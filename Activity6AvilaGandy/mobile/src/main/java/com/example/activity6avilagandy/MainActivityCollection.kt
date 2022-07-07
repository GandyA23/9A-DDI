package com.example.activity6avilagandy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.activity6avilagandy.databinding.ActivityMainCollectionBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class MainActivityCollection : AppCompatActivity() {

    companion object {
        // IMAGE AND LOG
        private const val TAG = "TestMainActivityDocument"
        private const val PICK_IMAGE_REQUEST = 71
        private const val DEF_COL = "Menu"

        // FIREBASE
        private val FIRES_REF = FirebaseStorage.getInstance().reference.child("images")
        private val MENU = Firebase.firestore.collection(DEF_COL)

        // ESTRUCTURA DOC
        const val TITLE = "title"
        const val IMAGE = "image"

        // Para obtener la estructura correcta y guardarla en firebase
        fun getHashDoc (title: String, image : String) : HashMap<String, String> {
            return hashMapOf(
                TITLE to title,
                IMAGE to image
            )
        }
    }

    private lateinit var binding: ActivityMainCollectionBinding

    // Elementos para imagen
    private lateinit var filePath: Uri
    private lateinit var imageView : ImageView
    private lateinit var btnChooseImage : Button

    // Inputs para la información
    private lateinit var titleEditTxt : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnSave : Button = binding.btnSend
        btnChooseImage = binding.chooseImageBtn
        imageView = binding.imageView
        titleEditTxt = binding.titleEditText

        // Configura el comportamiento del botón de la image
        btnChooseImage.setOnClickListener { launchGallery() }

        // Configura el comportamiento del botón de guardar
        btnSave.setOnClickListener { saveDataOnCollection() }
    }

    // Ayuda a seleccionar la imagen de la galeria
    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Selecciona una imagen"),
            PICK_IMAGE_REQUEST
        )
    }

    // Espera un resultado después de haber seleccionado una imagen
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if(data == null || data.data == null)
                return

            filePath = data.data!!
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // Guarda un documento en Firebase
    fun saveDataOnCollection () {
        // Guarda la imagen en Cloud Storage
        val uuid : String = UUID.randomUUID().toString()
        val ref = FIRES_REF.child("$DEF_COL/$uuid")
        val uploadTask = ref.putFile(filePath)

        uploadTask.addOnFailureListener {
            Toast.makeText(this@MainActivityCollection,
                "Error al cargar la imagen", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            val doc = getHashDoc(
                titleEditTxt.text.toString(),
                ref.path
            )

            // Guarda el documento en firebase
            MENU.document(uuid)
                .set(doc)
                .addOnSuccessListener {
                    Toast.makeText(this@MainActivityCollection,
                        "Se ha guardado correctamente", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "DocumentSnapshot successfully written!")

                    // Regresa al MainActivity
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@MainActivityCollection,
                        "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "Error writing document", e)
                }
        }

    }
}