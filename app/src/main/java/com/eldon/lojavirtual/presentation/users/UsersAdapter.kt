package com.eldon.lojavirtual.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.eldon.lojavirtual.databinding.ItemUserBinding
import com.eldon.lojavirtual.domain.model.User

class UsersAdapter :
    ListAdapter<User, UsersAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.imageUser.load(user.imageUrl)
            binding.textUserName.text = "${user.firstName} ${user.lastName}"
            binding.textUserEmail.text = user.email
            binding.textUserAge.text = "Idade: ${user.age} anos"
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}