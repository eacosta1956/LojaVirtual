package com.eldon.lojavirtual.presentation.products

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.eldon.lojavirtual.databinding.FragmentProductsBinding
import com.eldon.lojavirtual.presentation.productdetail.ProductDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.eldon.lojavirtual.R

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()
    private val productsAdapter = ProductsAdapter { product ->
        val arguments = bundleOf(
            ProductDetailFragment.PRODUCT_ID to product.id
        )

        findNavController().navigate(
            R.id.action_productsFragment_to_productDetailFragment,
            arguments
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        configureRecyclerView()
        observeUiState()

        binding.buttonRetry.setOnClickListener {
            viewModel.loadProducts()
        }
    }

    private fun configureRecyclerView() {
        binding.recyclerProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productsAdapter
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.progressProducts.isVisible = uiState.isLoading
                    binding.recyclerProducts.isVisible = uiState.products.isNotEmpty()
                    binding.layoutError.isVisible = uiState.error != null

                    binding.textError.text = uiState.error
                    productsAdapter.submitList(uiState.products)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}