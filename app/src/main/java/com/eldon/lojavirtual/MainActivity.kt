package com.eldon.lojavirtual

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eldon.lojavirtual.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}