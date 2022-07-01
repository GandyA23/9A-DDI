package com.example.activity5

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.activity5.databinding.ActivityMainDescriptionBinding

class MainActivityDescription : Activity() {

    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
    }

    private lateinit var binding: ActivityMainDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle : Bundle? = intent.extras
        val title : TextView = binding.title
        val description : TextView = binding.description

        title.text = bundle?.getString(TITLE).toString()
        description.text = bundle?.getString(DESCRIPTION).toString()
    }
}