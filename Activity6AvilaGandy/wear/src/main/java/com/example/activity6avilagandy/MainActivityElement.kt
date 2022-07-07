package com.example.activity6avilagandy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.activity6avilagandy.databinding.ActivityMainElementBinding
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class MainActivityElement : Activity() {

    companion object {
        // Nombres default para la estructura de cada uno de los documentos
        val COLLECTION : String = "collection"
        val TITLE : String = "title"
        val IMAGE : String = "image"
        val DESCRIPTION : String = "description"

        private val FIRES_REF = FirebaseStorage.getInstance().reference

        // Retorna un intent para ir a la vista vinculada a este activity
        fun getIntent(context : Context) : Intent {
            return Intent(context, MainActivityElement::class.java)
        }
    }

    private lateinit var binding: ActivityMainElementBinding

    /**
     * Setea los datos en la vista dinámica, esta vista es genérica y es
     * usada para mostrar cualquier documento de cualquier colección,
     * debido a que la estructura de todos los documentos (excepto la
     * colección de menú que no existe la descripción)
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainElementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obten las vistas del layout
        val title : TextView = binding.title
        val description : TextView = binding.description
        val image : ImageView = binding.image
        val bundle : Bundle? = intent.extras

        // Asigna los datos a su respectiva vista desde el bundle
        title.text = bundle?.getString(TITLE)
        description.text = bundle?.getString(DESCRIPTION)

        val imgSrc =  bundle?.getString(IMAGE)!!

        FIRES_REF.child(imgSrc).downloadUrl.addOnSuccessListener {
                it ->
            Picasso.get().load(it.normalizeScheme()).into(image)
        }
    }
}