package com.example.activity5.note

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.activity5.MainActivityDescription
import com.example.activity5.R
import com.example.activity5.databinding.ActivityPreviewRecyclerBinding

/**
 * This class is for configuration in any element in RecyclerView (loop) on next events:
 * - When it is created
 * - When it is binded data
 */
class NoteAdapter (val listNotes : MutableList<Note>, val context : Context) : RecyclerView.Adapter<NoteAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ActivityPreviewRecyclerBinding>(LayoutInflater.from(context), R.layout.activity_preview_recycler, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // position start in 0 to n-1
        // Set data to view in i position
        val note = listNotes[position]
        holder.binding.title.text = note.title

        holder.binding.root.setOnClickListener {
            // apply is to modify data
            // Allow modify data in context
            context.apply {
                // Prepare data to send Activity
                val intent = Intent(context, MainActivityDescription::class.java)

                intent.putExtra(MainActivityDescription.TITLE, note.title)
                intent.putExtra(MainActivityDescription.DESCRIPTION, note.description)

                startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    // binding : ActivityYourViewBinding
    class Holder (val binding : ActivityPreviewRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}