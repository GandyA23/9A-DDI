package com.example.activity6avilagandy.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.activity6avilagandy.MainActivity
import com.example.activity6avilagandy.MainActivityElement
import com.example.activity6avilagandy.R
import com.example.activity6avilagandy.databinding.ActivityMenuRecyclerBinding
import com.squareup.picasso.Picasso

class CollectionAdapter (val listCollection : MutableList<Collection>, val context : Context) : RecyclerView.Adapter<CollectionAdapter.Holder>()  {
    class Holder (val binding : ActivityMenuRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ActivityMenuRecyclerBinding>(LayoutInflater.from(context),
            R.layout.activity_menu_recycler, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val element : Collection = listCollection[position]

        holder.binding.title.text = element.title
        Picasso.get().load(element.image).into(holder.binding.iconMenu)

        holder.binding.root.setOnClickListener {
            context.apply {
                if (element.description.equals(MainActivity.DEFAULT_DESCRIPTION)) {
                    val intent = MainActivity.getIntent(this)
                    intent.putExtra(MainActivityElement.COLLECTION, element.title)
                    startActivity(intent)
                } else {
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