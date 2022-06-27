package com.example.activity5

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.activity5.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : Activity() {

    companion object {
        const val TAG = "TestMainActivity"
        private val NOTES = Firebase.firestore.collection("notes")
    }

    private lateinit var binding: ActivityMainBinding

    private fun testFirebase () {
        // Testing get a document once
        val docRef = NOTES.document("pl2ooxOWoQbWesSJfEbN")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        testFirebase()
    }
}
