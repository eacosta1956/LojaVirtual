package com.eldon.lojavirtual.presentation.carts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eldon.lojavirtual.databinding.ItemCartBinding
import com.eldon.lojavirtual.domain.model.Cart
import java.util.Locale

class CartsAdapter :
    ListAdapter<Cart, CartsAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cart: Cart) {
            binding.textCartTitle.text = "Carrinho #${cart.id}"
            binding.textCartUser.text = "Usuário #${cart.userId}"
            binding.textCartQuantity.text =
                "Itens: ${cart.totalQuantity} em ${cart.totalProducts} produtos"
            binding.textCartTotal.text = String.format(
                Locale("pt", "BR"),
                "Total: R$ %.2f",
                cart.discountedTotal
            )
        }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<Cart>() {

        override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem == newItem
        }
    }
}