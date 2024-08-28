package com.example.learning226.coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout.DispatchChangeEvent
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

class CoroutinesExample : AppCompatActivity() {

    private lateinit var image1: ImageView
    private lateinit var videoview: VideoView
    private lateinit var imgbtn: Button
    private lateinit var pdfbtn: Button
    private lateinit var vdobtn: Button
    private lateinit var text: TextView
    private val img1url = "https://stsci-opo.org/STScI-01HZ7HA3A3ZKSJJ90YCZPV7XQ2.png"
    private val imageUrl = "https://img.freepik.com/free-vector/night-time-sky-background-with-glittering-stars_1048-19664.jpg"
    private val pdfUrl = "https://www.iitk.ac.in/esc101/2009Jan/lecturenotes/timecomplexity/TimeComplexity_using_Big_O.pdf"
    private val videoUrl = "https://videos.pexels.com/video-files/3145223/3145223-uhd_2560_1440_30fps.mp4"
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_example)

        image1 = findViewById(R.id.coroutinesImage)
        imgbtn = findViewById(R.id.coroutinebtnimage)
        pdfbtn = findViewById(R.id.coroutinebtnpdf)
        vdobtn = findViewById(R.id.coroutinebtnvdo)
        text = findViewById(R.id.coroutinetextview)
        videoview = findViewById(R.id.coroutinevideoview)

        imgbtn.setOnClickListener{
            //Launch a coroutine in the LifecycleScope
            lifecycleScope.launch(Dispatchers.IO){
                val imageD = fetchImage(img1url)
                if(imageD != null){
                    // Decode byte array into bitmap
                    val bitmap = BitmapFactory.decodeByteArray(imageD, 0, imageD.size)
                    withContext(Dispatchers.Main){
                        image1.setImageBitmap(bitmap)
                    }
                }
            }
        }

        pdfbtn.setOnClickListener{
            //Launch a coroutine in the LifecycleScope
            lifecycleScope.launch(Dispatchers.IO){
                withContext(Dispatchers.Main){
                    text.text = "Downloading Pdf....."
                }
                val pdffile = downloadPdf(pdfUrl)
                if(pdffile != null){
                    withContext(Dispatchers.Main){
                        text.text = "Pdf Downloaded, Rendring"
                    }

                    val pdfBitmap = renderPdf(pdffile)
                    pdfBitmap?.let{
                        withContext(Dispatchers.Main){
                            image1.setImageBitmap(pdfBitmap)
                            text.text = "pdf rendered"
                        }
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        text.text = "Failed to Download Pdf"
                    }
                }
            }
        }

        vdobtn.setOnClickListener{
            lifecycleScope.launch {
                val videoUri = fetchVideoUrl(videoUrl)
                withContext(Dispatchers.Main) {
                    if (videoUri != null) {
                        videoview.setVideoURI(videoUri)
                        videoview.start()
                    } else {
                        // Handle error (e.g., show a Toast)
                        Toast.makeText(this@CoroutinesExample, "Video couldn't be loaded", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

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

    private suspend fun downloadPdf(url: String): File? {
        return withContext(Dispatchers.IO){
            val req = Request.Builder().url(url).build()
            val res = client.newCall(req).execute()
            if(res.isSuccessful){
                val pdfFile = File(cacheDir, "downloaded_pdf.pdf")
                val fos = FileOutputStream(pdfFile)
                res.body?.byteStream()?.use { inputStream ->
                    fos.use{ outputStream ->
                            inputStream.copyTo(outputStream)
                    }
                }
                pdfFile
            }
            else{
                null
            }
        }
    }

    private suspend fun renderPdf(file: File): Bitmap{
        return withContext(Dispatchers.IO){
            val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(fileDescriptor)
            val page = pdfRenderer.openPage(0)
            val w = page.width
            val h = page.height
            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            page.close()
            pdfRenderer.close()
            bitmap
        }
    }

    private suspend fun fetchVideoUrl(url: String): Uri? {
        return withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    // Returning the Uri directly for streaming
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
