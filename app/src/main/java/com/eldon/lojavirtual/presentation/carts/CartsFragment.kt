package com.eldon.lojavirtual.presentation.carts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eldon.lojavirtual.databinding.FragmentCartsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.eldon.lojavirtual.R
import com.eldon.lojavirtual.presentation.cartdetail.CartDetailFragment


@AndroidEntryPoint
class CartsFragment : Fragment() {

    private var _binding: FragmentCartsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartsViewModel by viewModels()
    private val cartsAdapter = CartsAdapter { cart ->
        // CartsFragment
        val arguments = Bundle().apply {
            putInt(CartDetailFragment.CART_ID, cart.id)
        }

        findNavController().navigate(
            R.id.action_cartsFragment_to_cartDetailFragment,
            arguments
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerCarts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCarts.adapter = cartsAdapter

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonRetryCarts.setOnClickListener {
            viewModel.loadCarts()
        }

        observeUiState()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressCarts.isVisible = state.isLoading
                    binding.recyclerCarts.isVisible =
                        !state.isLoading && state.error == null
                    binding.layoutErrorCarts.isVisible = state.error != null

                    state.error?.let { error ->
                        binding.textErrorCarts.text = error
                    }

                    cartsAdapter.submitList(state.carts)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}