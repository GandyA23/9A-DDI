package com.example.activity6avilagandy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.wear.widget.WearableLinearLayoutManager
import com.example.activity6avilagandy.databinding.ActivityMainBinding
import com.example.activity6avilagandy.model.Collection
import com.example.activity6avilagandy.model.CollectionAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : Activity() {

    companion object {
        const val TAG = "TestMainActivity"
        val COLLECTION : String = "collection"
        val TITLE : String = "title"
        val IMAGE : String = "image"
        val DESCRIPTION : String = "description"
        val DEFAULT_COLLECTION = "Menu"
        val DEFAULT_DESCRIPTION = "DOESNT_HAS_DESCRIPTION"
        private val FIREBASE_COLLECTION = Firebase.firestore

        fun getIntent(context : Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CollectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        var col : String? = bundle?.getString(COLLECTION)
        val listCollection : MutableList<Collection> = mutableListOf()

        if (col == null)
            col = DEFAULT_COLLECTION

        FIREBASE_COLLECTION.collection(col!!).get().addOnSuccessListener { document ->
            if (document != null) {
                for (doc in document) {
                    listCollection.add(
                        Collection(
                            doc.id,
                            doc.data[TITLE].toString(),
                            doc.data[IMAGE].toString(),
                            (if (doc.data[DESCRIPTION] != null) doc.data[DESCRIPTION] else DEFAULT_DESCRIPTION).toString()
                        )
                    )
                }

                // Set data to adapter and configurate
                adapter = CollectionAdapter(listCollection, this)

                // Center childs elements in Recycler View
                binding.recyclerView.isEdgeItemsCenteringEnabled = true

                // When the display is circular, do an degree effect in scroll
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
