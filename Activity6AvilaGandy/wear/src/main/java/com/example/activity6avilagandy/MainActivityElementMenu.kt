package com.example.activity6avilagandy

import android.app.Activity
import android.os.Bundle
import com.example.activity6avilagandy.databinding.ActivityMainElementMenuBinding

class MainActivityElementMenu : Activity() {

    companion object {
        val COLLECTION : String = "collection"
        val TITLE : String = "title"
        val IMAGE : String = "image"
    }

    private lateinit var binding: ActivityMainElementMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainElementMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}