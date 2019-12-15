package com.example.loadimage_asynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image = findViewById<ImageView>(R.id.imageView)
        button_load.setOnClickListener(){
            val download = DownloadImageTask(image)
            download.execute("https://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png")
        }
    }

    inner class DownloadImageTask(var bmImage: ImageView) :
        AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val urldisplay1 = urls[0]
            var mIcon11: Bitmap? = null
            try {
                if(isCancelled) return null
                val inu = java.net.URL(urldisplay1).openStream()
                mIcon11 = BitmapFactory.decodeStream(inu)
            } catch (e: Exception) {
                Log.e("Error1", e.message)
                e.printStackTrace()
            }
            return mIcon11
        }

        override fun onPostExecute(result: Bitmap) {
            bmImage.setImageBitmap(result)
        }
    }
}
