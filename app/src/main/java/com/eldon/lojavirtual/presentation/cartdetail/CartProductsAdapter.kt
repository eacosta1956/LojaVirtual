package com.eldon.lojavirtual.presentation.cartdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.eldon.lojavirtual.databinding.ItemCartProductBinding
import com.eldon.lojavirtual.domain.model.CartProduct
import java.util.Locale

class CartProductsAdapter :
    ListAdapter<CartProduct, CartProductsAdapter.CartProductViewHolder>(
        CartProductDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartProductViewHolder {
        val binding = ItemCartProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CartProductViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class CartProductViewHolder(
        private val binding: ItemCartProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: CartProduct) {
            binding.imageCartProduct.load(product.thumbnailUrl)
            binding.textCartProductTitle.text = product.title
            binding.textCartProductQuantity.text =
                "Quantidade: ${product.quantity}"
            binding.textCartProductTotal.text = String.format(
                Locale.forLanguageTag("pt-BR"),
                "Subtotal: R$ %.2f",
                product.discountedTotal
            )
        }
    }

    class CartProductDiffCallback : DiffUtil.ItemCallback<CartProduct>() {

        override fun areItemsTheSame(
            oldItem: CartProduct,
            newItem: CartProduct
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CartProduct,
            newItem: CartProduct
        ): Boolean {
            return oldItem == newItem
        }
    }
}