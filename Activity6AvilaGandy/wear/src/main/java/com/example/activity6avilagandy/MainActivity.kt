package com.example.activity6avilagandy

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.wear.widget.WearableLinearLayoutManager
import com.example.activity6avilagandy.collection.Menu
import com.example.activity6avilagandy.adapter.MenuAdapter
import com.example.activity6avilagandy.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : Activity() {

    companion object {
        const val TAG = "TestMainActivity"
        private val MENU = Firebase.firestore.collection("Menu")
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listMenu : MutableList<Menu> = mutableListOf()

        MENU.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (doc in document)
                        listMenu.add(Menu(
                            doc.id,
                            doc.data[MainActivityElementMenu.TITLE].toString(),
                            doc.data[MainActivityElementMenu.IMAGE].toString()
                        ))

                    // Set data to adapter and configurate
                    adapter = MenuAdapter(listMenu, this)

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