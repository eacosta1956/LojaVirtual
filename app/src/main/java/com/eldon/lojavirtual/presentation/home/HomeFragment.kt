package com.eldon.lojavirtual.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eldon.lojavirtual.R
import com.eldon.lojavirtual.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureClickListeners()
    }

    private fun configureClickListeners() {
        binding.buttonProducts.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_productsFragment
            )
        }

        binding.buttonCarts.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_cartsFragment
            )
        }

        binding.buttonUsers.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_usersFragment
            )
        }

        binding.buttonPosts.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_postsFragment
            )
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}