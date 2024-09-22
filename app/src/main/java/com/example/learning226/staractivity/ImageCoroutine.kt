package com.example.learning226.staractivity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class ImageCoroutine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_coroutine)
        val img = findViewById<ImageView>(R.id.starcourseImageview)
        var imgurl = "https://img.freepik.com/free-vector/scan-me-qr-code_78370-2915.jpg"

        lifecycleScope.launch(Dispatchers.IO){
            val imageD = fetchImage(imgurl)
            withContext(Dispatchers.Main){
                if(imageD != null){
                    // Decode byte array into bitmap
                    delay(1000)

                    val bitmap = BitmapFactory.decodeByteArray(imageD, 0, imageD.size)

                    img.setImageBitmap(bitmap)
                }
            }
        }
    }
    private suspend fun fetchImage(url: String): ByteArray?{
        return withContext(Dispatchers.IO){
            val req = Request.Builder().url(url).build()
            val client = OkHttpClient()
            val res = client.newCall(req).execute()

            if(res.isSuccessful){
                res.body?.bytes()
            }
            else{
                null
            }
        }
    }
}