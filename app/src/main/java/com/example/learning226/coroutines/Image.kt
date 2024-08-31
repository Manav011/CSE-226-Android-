package com.example.learning226.coroutines

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * A simple [Fragment] subclass.
 * Use the [Image.newInstance] factory method to
 * create an instance of this fragment.
 */
class Image : Fragment() {
    private lateinit var btn: Button
    private lateinit var imgview: ImageView
    private lateinit var status: TextView
    private lateinit var link: EditText
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_image, container, false)
        btn = view.findViewById(R.id.coroutineimagebtn)
        imgview = view.findViewById(R.id.coroutinesImageview)
        link = view.findViewById(R.id.coroutineimagelink)
        status = view.findViewById(R.id.coroutinesimgstatus)
        var imgurl = "https://stsci-opo.org/STScI-01HZ7HA3A3ZKSJJ90YCZPV7XQ2.png"
        val imageUrl = "https://img.freepik.com/free-vector/night-time-sky-background-with-glittering-stars_1048-19664.jpg"

        btn.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                withContext(Dispatchers.Main){
                    status.text = "Downloading Image..."
                }

                var lnktxt = link.text.toString()
                if(lnktxt != "" && lnktxt.endsWith(".png")){
                    imgurl = lnktxt
                }

                val imageD = fetchImage(imgurl)
                if(imageD != null){
                    // Decode byte array into bitmap
                    withContext(Dispatchers.Main){
                        status.text = "Downloaded, Rendering..."
                    }
                    val bitmap = BitmapFactory.decodeByteArray(imageD, 0, imageD.size)
                    withContext(Dispatchers.Main){
                        imgview.setImageBitmap(bitmap)
                        status.text = "Rendered"
                    }
                }
                else{
                    withContext(Dispatchers.Main){
                        status.text = "Failed to Download Image"
                    }
                }
            }
    }

        return view
    }

    private suspend fun fetchImage(url: String): ByteArray?{
        return withContext(Dispatchers.IO){
            val req = Request.Builder().url(url).build()
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