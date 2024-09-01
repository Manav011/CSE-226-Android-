package com.example.learning226.coroutines

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

/**
 * A simple [Fragment] subclass.
 * Use the [Audio.newInstance] factory method to
 * create an instance of this fragment.
 */
class Audio : Fragment() {
    private lateinit var link: EditText
    private lateinit var btn: Button
    private lateinit var status: TextView
    private var mediaPlayer: MediaPlayer? = null
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_audio, container, false)

        link = view.findViewById(R.id.coroutineaudiolink)
        btn = view.findViewById(R.id.coroutineaudiobtn)
        status = view.findViewById(R.id.coroutinesaudiostatus)

        btn.setOnClickListener{
            fetchAndPlay()
        }

        return view
    }

    private fun fetchAndPlay(){
        var audioUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3"
        var lnktxt = link.text.toString()
        if(lnktxt != "" && lnktxt.endsWith(".mp3")){
            audioUrl = lnktxt
        }
        else{
            status.text = "Invalid Link Provided, Downloading default audio..."
        }
        status.text = status.text.toString() + "\nDownloading...."

        lifecycleScope.launch(Dispatchers.IO){
            val audioData = fetch(audioUrl)
            withContext(Dispatchers.Main){
                if(audioData != null){
                    status.text = "Downloaded, Rendering..."
                    delay(1000)

                    playAudio(audioData)

                    status.text = "Playing..."

                }
                else{
                    status.text = "Failed to Download Audio"

                }
            }
        }
    }

    private suspend fun fetch(url: String): ByteArray? {
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

    private fun playAudio(audioData: ByteArray){
        val audioFile = File.createTempFile("temp_audio", "mp3", activity?.cacheDir)
        audioFile.deleteOnExit()
        audioFile.outputStream().use{it.write(audioData)}

        mediaPlayer = MediaPlayer().apply{
            setDataSource(audioFile.absolutePath)
            prepare()
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}