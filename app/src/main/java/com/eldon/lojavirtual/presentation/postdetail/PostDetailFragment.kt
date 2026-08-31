package com.eldon.lojavirtual.presentation.postdetail

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
import com.eldon.lojavirtual.databinding.FragmentPostDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = requireArguments().getInt(POST_ID, -1)

        if (postId == -1) {
            Toast.makeText(
                requireContext(),
                "Postagem não encontrada.",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().popBackStack()
            return
        }

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonRetryPostDetail.setOnClickListener {
            viewModel.loadPost(postId)
        }

        observeUiState()
        viewModel.loadPost(postId)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressPostDetail.isVisible = state.isLoading
                    binding.layoutErrorPostDetail.isVisible = state.error != null

                    state.post?.let { post ->
                        binding.textPostDetailTitle.text = post.title
                        binding.textPostDetailBody.text = post.body
                        binding.textPostDetailTags.text =
                            post.tags.joinToString(" ") { tag -> "#$tag" }
                        binding.textPostDetailUser.text = "Usuário #${post.userId}"
                        binding.textPostDetailReactions.text =
                            "Curtidas: ${post.reactions.likes} • " +
                                    "Não gostei: ${post.reactions.dislikes}"
                        binding.textPostDetailViews.text =
                            "Visualizações: ${post.views}"
                    }

                    state.error?.let { error ->
                        binding.textErrorPostDetail.text = error
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
        const val POST_ID = "post_id"
    }
}