package com.example.activity4

import android.app.Activity
import android.os.Bundle
import com.example.activity4.databinding.ActivityMainBookBinding

class MainActivityBook : Activity() {

    private lateinit var binding: ActivityMainBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_book)

        binding = ActivityMainBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}