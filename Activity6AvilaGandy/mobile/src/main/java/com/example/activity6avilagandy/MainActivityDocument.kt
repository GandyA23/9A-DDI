package com.example.activity6avilagandy

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.activity6avilagandy.databinding.ActivityMainDocumentBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class MainActivityDocument : AppCompatActivity() {

    companion object {
        // IMAGE AND LOG
        private const val TAG = "TestMainActivityDocument"
        private const val ERROR_TAG = "Failed!"
        private const val PICK_IMAGE_REQUEST = 71

        // FIREBASE
        private val FIRES_REF = FirebaseStorage.getInstance().reference.child("images")
        private val FIREB = Firebase.firestore
        private val MENU = FIREB.collection("Menu")

        // ESTRUCTURA DOC
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE = "image"

        // Para obtener la estructura correcta y guardarla en firebase
        fun getHashDoc (title: String, description: String, image : String) : HashMap<String, String> {
            return hashMapOf(
                TITLE to title,
                DESCRIPTION to description,
                IMAGE to image
            )
        }
    }

    private lateinit var binding: ActivityMainDocumentBinding

    // Elemento para colección
    private lateinit var collectionName: String

    // Elementos para imagen
    private lateinit var filePath: Uri
    private lateinit var imageView : ImageView
    private lateinit var btnChooseImage : Button

    // Inputs para la información
    private lateinit var titleEditTxt : EditText
    private lateinit var descriptionEditTxt : EditText

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDocumentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnSave : Button = binding.btnSend
        btnChooseImage = binding.chooseImageBtn
        imageView = binding.imageView
        titleEditTxt = binding.titleEditText
        descriptionEditTxt = binding.descriptionEditText

        // Crea los radioButtos para que seleccione una colección
        getTitlesInMenu()

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
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST)
    }

    // Consulta las colecciones disponibles para guardar un documento
    fun getTitlesInMenu () {
        var index = 1
        val radioGroup = binding.collectionRadioGroup

        MENU.get().addOnSuccessListener { documents ->
            // Por cada documento, crea un nuevo RadioButton
            for (doc in documents) {
                // Crea y configura el RadioButton
                val radioButton = RadioButton(this)

                if (index.equals(1)) {
                    radioButton.isChecked = true
                    collectionName = doc.data["title"].toString()
                }

                radioButton.id = index++
                radioButton.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                radioButton.setText(doc.data["title"].toString())
                radioButton.setTextColor(resources.getColor(R.color.black, null))

                // Añadelo al papá
                radioGroup.addView(radioButton)
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                var radio : RadioButton = findViewById(checkedId)
                collectionName = radio.text.toString()
            }
        }.addOnFailureListener {
            Log.e(ERROR_TAG, "Error querying to firebase")
        }
    }

    // Guarda un documento en Firebase
    fun saveDataOnCollection () {
        val uuid : String = UUID.randomUUID().toString()

        val ref = FIRES_REF.child("$collectionName/$uuid")
        val uploadTask = ref.putFile(filePath)

        uploadTask.addOnFailureListener {
            Toast.makeText(this@MainActivityDocument,
                "Error al cargar la imagen", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            val doc = getHashDoc(
                titleEditTxt.text.toString(),
                descriptionEditTxt.text.toString(),
                ref.path
            )

            // Guarda el documento en firebase
            FIREB.collection(collectionName).document(uuid)
                .set(doc)
                .addOnSuccessListener {
                    Toast.makeText(this@MainActivityDocument,
                        "Se ha guardado correctamente", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "DocumentSnapshot successfully written!")

                    // Regresa al MainActivity
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@MainActivityDocument,
                        "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "Error writing document", e)
                }
        }

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
}