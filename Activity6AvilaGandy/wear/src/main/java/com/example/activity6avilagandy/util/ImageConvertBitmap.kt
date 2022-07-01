package com.example.activity6avilagandy.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

class ImageConvertBitmap {
    companion object {
        fun convert (urlImage : String) : Bitmap? {
            val executor = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())
            var image: Bitmap? = null

            executor.execute {
                val imageURL = urlImage

                try {
                    val `in` = java.net.URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)

                    handler.post {
                    }
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            return image
        }
    }
}