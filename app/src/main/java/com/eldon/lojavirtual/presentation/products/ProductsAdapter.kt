package com.eldon.lojavirtual.presentation.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.eldon.lojavirtual.R
import com.eldon.lojavirtual.databinding.ItemProductBinding
import com.eldon.lojavirtual.domain.model.Product

class ProductsAdapter(
    private val onProductClick: (Product) -> Unit
) : ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.textProductTitle.text = product.title
            binding.textProductCategory.text = product.category
            binding.textProductPrice.text = binding.root.context.getString(
                R.string.product_price,
                product.price
            )
            binding.textProductRating.text = binding.root.context.getString(
                R.string.product_rating,
                product.rating
            )

            binding.imageProduct.load(product.thumbnailUrl)

            binding.root.setOnClickListener {
                onProductClick(product)
            }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}