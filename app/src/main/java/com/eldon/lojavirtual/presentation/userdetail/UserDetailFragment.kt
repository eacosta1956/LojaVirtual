package com.eldon.lojavirtual.presentation.userdetail

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
import coil3.load
import com.eldon.lojavirtual.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = requireArguments().getInt(USER_ID, -1)

        if (userId == -1) {
            Toast.makeText(
                requireContext(),
                "Usuário não encontrado.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonRetryUserDetail.setOnClickListener {
            viewModel.loadUser(userId)
        }

        observeUiState()
        viewModel.loadUser(userId)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressUserDetail.isVisible = state.isLoading
                    binding.layoutErrorUserDetail.isVisible = state.error != null

                    state.user?.let { user ->
                        binding.imageUserDetail.load(user.imageUrl)
                        binding.textUserDetailName.text =
                            "${user.firstName} ${user.lastName}"
                        binding.textUserDetailEmail.text = "E-mail: ${user.email}"
                        binding.textUserDetailPhone.text = "Telefone: ${user.phone}"
                        binding.textUserDetailAge.text =
                            "Idade: ${user.age} anos • ${user.gender}"
                        binding.textUserDetailAddress.text =
                            "Endereço: ${user.address}, ${user.city} - " +
                                    "${user.state}, ${user.country}"
                        binding.textUserDetailUniversity.text =
                            "Universidade: ${user.university}"
                        binding.textUserDetailCompany.text =
                            "Empresa: ${user.companyName} — ${user.companyTitle}"
                    }

                    state.error?.let { error ->
                        binding.textErrorUserDetail.text = error
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
        const val USER_ID = "user_id"
    }
}