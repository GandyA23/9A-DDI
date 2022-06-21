package com.example.activity4

import android.app.Activity
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.activity4.databinding.ActivityMainJokeBinding

class MainActivityJoke : Activity() {
    private lateinit var binding: ActivityMainJokeBinding
    private val JOKES : List<String> = listOf("¿Cuál es el último animal que subió al arca de Noé? El del-fin.",
        "¿Cómo se dice pañuelo en japonés? Saka-moko.", "¿Cómo se dice disparo en árabe? Ahí-va-la-bala.")

    private fun check (pos : Int) : Boolean {
        return pos >= 0 && pos < JOKES.size
    }

    private fun getDrawableJoke (no : Int) : Drawable? {
        return ResourcesCompat.getDrawable(
            resources,
            when (no) {
                1 -> R.drawable.ic_joke1
                2 -> R.drawable.ic_joke2
                else -> R.drawable.ic_joke3
            },
            null
        )
    }

    private fun setData (text : TextView, noJoke : TextView, bg : View, pos : Int) {
        text.text = JOKES.get(pos)
        bg.background = getDrawableJoke(pos + 1)
        noJoke.text = "${pos + 1}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jokePos = 0
        val next : Button = binding.btnNext
        val previous : Button = binding.btnPrevious
        val textViewJoke : TextView = binding.textJoke
        val textViewNoJoke : TextView = binding.textNoJoke
        val viewJoke : View = binding.viewJoke

        setData(textViewJoke, textViewNoJoke, viewJoke, jokePos)

        next.setOnClickListener {
            if (check(jokePos + 1))
                setData(textViewJoke, textViewNoJoke, viewJoke, ++jokePos)
        }

        previous.setOnClickListener {
            if (check(jokePos - 1))
                setData(textViewJoke, textViewNoJoke, viewJoke, --jokePos)
        }
    }
}