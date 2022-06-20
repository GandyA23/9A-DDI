package com.example.activity4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.activity4.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnBook : Button = binding.btnBook
        val btnHeartBeat : Button = binding.btnHeart
        val btnJoke : Button = binding.btnJoke

        btnBook.setOnClickListener {
            // De mi vista (this) ve a la vista de book (MainActivityBook)
            val intent = Intent(this, MainActivityBook::class.java)

            // Pasar datos a mi MainActivity
            // intent.putExtra("fullname", "Ávila Estrada Gandy Esaú")

            // Vamos a mi vista
            startActivity(intent)

            // Mostrar errores o warnings en LogCat
            Log.w("warning_intent", "Ha ocurrido un warning")

            Log.v("error_intent", "Ha ocurrido un error")
        }

        btnJoke.setOnClickListener {
            // De mi vista (this) ve a la vista de heart beat (MainActivityJoke)
            val intent = Intent(this, MainActivityJoke::class.java)

            // Vamos a mi vista
            startActivity(intent)
        }
    }
}