package com.eldon.lojavirtual.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eldon.lojavirtual.databinding.ItemPostBinding
import com.eldon.lojavirtual.domain.model.Post

class PostsAdapter(
    private val onPostClick: (Post) -> Unit = {}
) : ListAdapter<Post, PostsAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.textPostTitle.text = post.title
            binding.textPostBody.text = post.body
            binding.textPostTags.text = post.tags.joinToString(" ") { tag -> "#$tag" }
            binding.textPostInfo.text =
                "Curtidas: ${post.reactions.likes} • Não gostei: " +
                        "${post.reactions.dislikes} • Visualizações: ${post.views}"

            binding.root.setOnClickListener {
                onPostClick(post)
            }
        }
    }

    private class PostDiffCallback : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}