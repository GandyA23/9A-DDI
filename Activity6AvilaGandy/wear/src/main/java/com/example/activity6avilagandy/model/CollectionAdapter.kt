package com.example.activity6avilagandy.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.activity6avilagandy.MainActivity
import com.example.activity6avilagandy.MainActivityElement
import com.example.activity6avilagandy.R
import com.example.activity6avilagandy.databinding.ActivityMenuRecyclerBinding
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class CollectionAdapter (val listCollection : MutableList<Collection>, val context : Context) : RecyclerView.Adapter<CollectionAdapter.Holder>()  {
    companion object {
        private val FIRES_REF = FirebaseStorage.getInstance().reference
    }

    class Holder (val binding : ActivityMenuRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ActivityMenuRecyclerBinding>(LayoutInflater.from(context),
            R.layout.activity_menu_recycler, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val element : Collection = listCollection[position]

        // Asigna la foto y el título al elemento en el recycler view
        holder.binding.title.text = element.title

        FIRES_REF.child(element.image).downloadUrl.addOnSuccessListener {
            it ->
            Picasso.get().load(it.normalizeScheme()).into(holder.binding.iconMenu)
        }


        holder.binding.root.setOnClickListener {
            // Dependiendo la estructura del documento, realiza una acción
            context.apply {
                if (element.description.equals(MainActivity.DEFAULT_DESCRIPTION)) {
                    /**
                     * Si el documento no cuenta con una descripción, entonces
                     * quiere decir que te encuentras en el menú principal,
                     * cuando se le de click a este elemento, entonces vas a
                     * mostrar los documentos de esa colección
                     * */
                    val intent = MainActivity.getIntent(this)
                    intent.putExtra(MainActivityElement.COLLECTION, element.title)
                    startActivity(intent)
                } else {
                    /**
                     * En caso contrario, quiere decir que quieres mostrar un
                     * documento en específico, entonces cuando de click, envía
                     * la información a MainActivityElement para mostrarla.
                     * */
                    val intent = MainActivityElement.getIntent(this)
                    intent.putExtra(MainActivityElement.TITLE, element.title)
                    intent.putExtra(MainActivityElement.DESCRIPTION, element.description)
                    intent.putExtra(MainActivityElement.IMAGE, element.image)
                    intent.putExtra(MainActivityElement.COLLECTION, element.title)
                    startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listCollection.size
    }
}