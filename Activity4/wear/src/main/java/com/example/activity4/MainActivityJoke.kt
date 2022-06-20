package com.example.activity4

import android.app.Activity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.widget.Button
import android.widget.TextView
import com.example.activity4.databinding.ActivityMainJokeBinding

class MainActivityJoke : Activity() {
    companion object {
        val JOKES : List<String> = listOf("¿Cuál es el último animal que subió al arca de Noé? El del-fin.",
            "¿Cómo se dice pañuelo en japonés? Saka-moko.", "¿Cómo se dice disparo en árabe? Ahí-va-la-bala.")
    }

    private lateinit var binding: ActivityMainJokeBinding

    fun check (pos : Int) : Boolean {
        return pos >= 0 && pos < JOKES.size
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jokePos = 0
        val next : Button = binding.btnNext
        val previous : Button = binding.btnPrevious
        val textViewJoke : TextView = binding.textJoke

        textViewJoke.text = JOKES[0]

        next.setOnClickListener {
            if (check(jokePos + 1)) {
                textViewJoke.text = JOKES[++jokePos]
            }
        }

        previous.setOnClickListener {
            if (check(jokePos - 1)) {
                textViewJoke.text = JOKES[--jokePos]
            }
        }
    }
}