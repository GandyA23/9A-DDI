package com.example.activity5

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.wear.widget.WearableLinearLayoutManager
import com.example.activity5.databinding.ActivityMainBinding
import com.example.activity5.note.Note
import com.example.activity5.note.NoteAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : Activity() {

    companion object {
        const val TAG = "TestMainActivity"
        private val NOTES = Firebase.firestore.collection("notes")
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listNotes = ArrayList<Note>()

        NOTES.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (doc in document)
                        listNotes.add(Note(
                            doc.id,
                            doc.data[MainActivityDescription.TITLE].toString(),
                            doc.data[MainActivityDescription.DESCRIPTION].toString()
                        ))
                    // Set data to adapter and configurate
                    adapter = NoteAdapter(listNotes, this)

                    // Center childs elements in Recycler View
                    binding.recyclerView.isEdgeItemsCenteringEnabled = true

                    // When the display is circular, do an 180° effect in scroll
                    binding.recyclerView.isCircularScrollingGestureEnabled = true

                    binding.recyclerView.layoutManager = WearableLinearLayoutManager(this)
                    binding.recyclerView.adapter = adapter
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}
