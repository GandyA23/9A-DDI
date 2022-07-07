package com.example.activity6avilagandy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activity6avilagandy.databinding.ActivityMainCollectionBinding

class MainActivityCollection : AppCompatActivity() {

    private lateinit var binding: ActivityMainCollectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}