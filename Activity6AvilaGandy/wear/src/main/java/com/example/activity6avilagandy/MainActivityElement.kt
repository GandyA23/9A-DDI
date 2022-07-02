package com.example.activity6avilagandy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.activity6avilagandy.databinding.ActivityMainElementBinding
import com.squareup.picasso.Picasso

class MainActivityElement : Activity() {

    companion object {
        val COLLECTION : String = "collection"
        val TITLE : String = "title"
        val IMAGE : String = "image"
        val DESCRIPTION : String = "description"

        fun getIntent(context : Context) : Intent {
            return Intent(context, MainActivityElement::class.java)
        }
    }

    private lateinit var binding: ActivityMainElementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainElementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title : TextView = binding.title
        val description : TextView = binding.description
        val image : ImageView = binding.image
        val bundle : Bundle? = intent.extras

        title.text = bundle?.getString(TITLE)
        description.text = bundle?.getString(DESCRIPTION)
        Picasso.get().load(bundle?.getString(IMAGE)).into(image)
    }
}