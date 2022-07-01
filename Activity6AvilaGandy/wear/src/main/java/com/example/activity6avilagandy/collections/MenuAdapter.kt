package com.example.activity6avilagandy.collections

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.activity6avilagandy.MainActivityElementMenu
import com.example.activity6avilagandy.R
import com.example.activity6avilagandy.databinding.ActivityMenuRecyclerBinding
import com.example.activity6avilagandy.util.ImageConvertBitmap

class MenuAdapter (val listMenu : MutableList<Menu>, val context : Context) : RecyclerView.Adapter<MenuAdapter.Holder>()  {
    class Holder (val binding : ActivityMenuRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ActivityMenuRecyclerBinding>(LayoutInflater.from(context), R.layout.activity_menu_recycler, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val element : Menu = listMenu[position]

        // Set title and image
        holder.binding.title.text = element.title
        holder.binding.iconMenu.setImageBitmap(ImageConvertBitmap.convert(element.image))

        holder.binding.root.setOnClickListener {
            context.apply {
                val intent = Intent(context, MainActivityElementMenu::class.java)
                intent.putExtra(MainActivityElementMenu.COLLECTION, element.title)
                startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}