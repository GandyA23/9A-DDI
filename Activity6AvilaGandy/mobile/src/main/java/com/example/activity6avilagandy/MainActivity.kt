package com.example.activity6avilagandy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.activity6avilagandy.databinding.ActivityMainBinding
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Realiza la redirección a su respectiva vista.
         * En caso de agregar más botones, favor de agregar aquí con su respectivo
         * Activity a donde ir
         * */
        val buttons : Map<KClass<out AppCompatActivity>, Button> = mapOf(
            Pair(MainActivityCollection::class, binding.option1Btn),
            Pair(MainActivityDocument::class, binding.option2Btn)
        )

        /**
         * Como los botones son utilizados para la redirección hacia otro
         * Activity, es posible almacenarlos en un mapa para evitar escribir
         * la misma función varias veces, en caso de que sea necesario de
         * realizar otra acción antes de ir al Activity, entonces cambiar
         * esta forma.
         * */
        for ((activity, button) in buttons)
            button.setOnClickListener {
                startActivity(Intent(this, activity.java))
            }
    }
}
