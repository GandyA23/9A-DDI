package com.example.activity4

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.activity4.databinding.ActivityMainHeartBeatBinding

class MainActivityHeartBeat : Activity() {

    companion object {
        const val BEAT = "beat"
    }

    private lateinit var binding: ActivityMainHeartBeatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainHeartBeatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener los datos enviados desde otras vistas
        val bundle = intent.extras
        val beat = bundle?.getString(BEAT)

        val textHeartBeat : TextView = binding.textHeartBeat
        textHeartBeat.text = beat
    }
}