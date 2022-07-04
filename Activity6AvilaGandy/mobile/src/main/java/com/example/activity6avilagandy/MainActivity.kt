package com.example.activity6avilagandy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSend : Button = findViewById(R.id.btnSend)
        val collectionRadio : RadioGroup = findViewById(R.id.collectionGroup);

        collectionRadio.setOnCheckedChangeListener { group, checkedId -> {

        }}

        btnSend.setOnClickListener {
            // Collect data from edittext
            val title : EditText = findViewById(R.id.titleEditText)
            val urlImage : EditText = findViewById(R.id.imageEditText)
            val description : EditText = findViewById(R.id.descriptionEditText)
        }
    }
}
