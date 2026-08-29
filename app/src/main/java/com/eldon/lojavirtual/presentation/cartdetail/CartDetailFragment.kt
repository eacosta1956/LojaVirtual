package com.eldon.lojavirtual.presentation.cartdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eldon.lojavirtual.databinding.FragmentCartDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CartDetailFragment : Fragment() {

    private var _binding: FragmentCartDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartDetailViewModel by viewModels()
    private val productsAdapter = CartProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartId = requireArguments().getInt(CART_ID, -1)

        if (cartId == -1) {
            Toast.makeText(
                requireContext(),
                "Carrinho não encontrado.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        binding.recyclerCartProducts.layoutManager =
            LinearLayoutManager(requireContext())
        binding.recyclerCartProducts.adapter = productsAdapter

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonRetryCartDetail.setOnClickListener {
            viewModel.loadCart(cartId)
        }

        observeUiState()
        viewModel.loadCart(cartId)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state: CartDetailUiState ->
                    binding.progressCartDetail.isVisible = state.isLoading
                    binding.layoutErrorCartDetail.isVisible = state.error != null

                    state.cart?.let { cart ->
                        binding.textCartDetailTitle.text = "Carrinho #${cart.id}"
                        binding.textCartDetailUser.text = "Usuário #${cart.userId}"
                        binding.textCartDetailQuantity.text =
                            "Itens: ${cart.totalQuantity} em ${cart.totalProducts} produtos"
                        binding.textCartDetailTotal.text = String.format(
                            Locale.forLanguageTag("pt-BR"),
                            "Total: R$ %.2f",
                            cart.discountedTotal
                        )
                        productsAdapter.submitList(cart.products)
                    }

                    state.error?.let { error ->
                        binding.textErrorCartDetail.text = error
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CART_ID = "cart_id"
    }
}