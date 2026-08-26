package com.eldon.lojavirtual.presentation.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil3.load
import com.eldon.lojavirtual.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val productId = requireArguments().getInt(PRODUCT_ID, -1)

        if (productId == -1) {
            Toast.makeText(
                requireContext(),
                "Produto não encontrado.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        observeUiState()
        viewModel.loadProduct(productId)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state.product?.let { product ->
                        binding.imageProductDetail.load(product.thumbnailUrl)
                        binding.textProductTitle.text = product.title
                        binding.textProductCategory.text = product.category
                        binding.textProductPrice.text = String.format(
                            Locale("pt", "BR"),
                            "R$ %.2f",
                            product.price
                        )
                        binding.textProductRating.text = String.format(
                            Locale("pt", "BR"),
                            "Avaliação: %.1f",
                            product.rating
                        )
                        binding.textProductDescription.text = product.description
                    }

                    state.error?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
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
        const val PRODUCT_ID = "product_id"
    }
}