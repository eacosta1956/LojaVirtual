package com.eldon.lojavirtual

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eldon.lojavirtual.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureClickListeners()
    }

    private fun configureClickListeners() {
        binding.buttonProducts.setOnClickListener {
            showMessage("Produtos")
        }

        binding.buttonCarts.setOnClickListener {
            showMessage("Carrinho")
        }

        binding.buttonUsers.setOnClickListener {
            showMessage("Usuários")
        }

        binding.buttonPosts.setOnClickListener {
            showMessage("Postagens")
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}