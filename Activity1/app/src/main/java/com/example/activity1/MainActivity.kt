package com.example.activity1

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import com.example.activity1.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Consigue la vista dentro del binding para añadir funcionalidad
        val btnPlay : Button = binding.btnPlay
        val btnStop : Button = binding.btnStop
        val btnReplay : Button = binding.btnReplay
        val chronometer : Chronometer = binding.chronometer

        // Inicia
        btnPlay.setOnClickListener {
            btnPlay.isEnabled = false
            btnStop.isEnabled = true
            btnReplay.isEnabled = true

            // El tiempo del cronómetro se inicializa en 0
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
        }
    
        // Detente
        btnStop.setOnClickListener {
            btnPlay.isEnabled = true
            btnStop.isEnabled = false
            btnReplay.isEnabled = false

            // El tiempo del cronómetro se inicializa en 0
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.stop()
        }

        // Reinicia
        btnReplay.setOnClickListener {
            chronometer.stop()
            // El tiempo del cronómetro se inicializa en 0
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
        }
    }
}
