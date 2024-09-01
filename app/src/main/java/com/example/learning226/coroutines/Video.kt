package com.example.learning226.coroutines

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * A simple [Fragment] subclass.
 * Use the [Video.newInstance] factory method to
 * create an instance of this fragment.
 */
class Video : Fragment() {
    private lateinit var videoview: VideoView
    private lateinit var btn: Button
    private lateinit var progbtn: Button
    private lateinit var status: TextView
    private lateinit var link: EditText
    private lateinit var progressbar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_video, container, false)

        link = view.findViewById(R.id.coroutinevideolink)
        btn = view.findViewById(R.id.coroutinevideobtn)
        progbtn = view.findViewById(R.id.coroutinevideobtn1)
        status = view.findViewById(R.id.coroutinevdostatus)
        videoview = view.findViewById(R.id.coroutinesVideoview)
        progressbar = view.findViewById(R.id.coroutinesvideoprogressbar)
        var videoUrl = "https://videos.pexels.com/video-files/3145223/3145223-uhd_2560_1440_30fps.mp4"

        btn.setOnClickListener{

            var lnktxt = link.text.toString()
            if(lnktxt != "" && lnktxt.endsWith(".mp4")){
                videoUrl = lnktxt
            }else{
                status.text = "Invalid Link Provided, Downloading Default video..."
            }
            status.text = status.text.toString() + "\nDownloading Video..."

            lifecycleScope.launch {
                val videoUri = fetchVideoUrl(videoUrl)
                withContext(Dispatchers.Main) {
                    if (videoUri != null) {
                        status.text = "Video Downloaded, Rendring"
                        delay(1000)

                        videoview.setVideoURI(videoUri)
                        videoview.start()

                        status.text = "Rendered"
                    } else {
                        status.text = "Failed to Download Video"
                    }
                }
            }
        }

        // Class code
        progbtn.setOnClickListener{
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoview)
            videoview.setMediaController(mediaController)

            progressbar.visibility = ProgressBar.VISIBLE

            var lnktxt = link.text.toString()
            if(lnktxt != "" && lnktxt.endsWith(".mp4")){
                videoUrl = lnktxt
            }

            CoroutineScope(Dispatchers.IO).launch{
                withContext(Dispatchers.Main){
                    videoview.setVideoPath(videoUrl)
                    videoview.start()
                }
            }
        }

        return view
    }

    private suspend fun fetchVideoUrl(url: String): Uri? {
        return withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main){
                        status.text = "Started Video"
                    }
                    Uri.parse(url)
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}