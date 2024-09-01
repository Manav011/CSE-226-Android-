package com.example.learning226.coroutines

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

/**
 * A simple [Fragment] subclass.
 * Use the [PdfDoc.newInstance] factory method to
 * create an instance of this fragment.
 */
class PdfDoc : Fragment() {
    private lateinit var link: EditText
    private lateinit var btn: Button
    private lateinit var status: TextView
    private lateinit var view: ImageView
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val retv =  inflater.inflate(R.layout.fragment_pdf_doc, container, false)

        link = retv.findViewById(R.id.coroutinePdflink)
        btn = retv.findViewById(R.id.coroutinepdfbtn)
        status = retv.findViewById(R.id.coroutinespdfstatus)
        view = retv.findViewById(R.id.coroutinespdfview)

        btn.setOnClickListener{
            var pdfUrl = "https://www.iitk.ac.in/esc101/2009Jan/lecturenotes/timecomplexity/TimeComplexity_using_Big_O.pdf"
            var lnktxt = link.text.toString()
            if(lnktxt != "" && lnktxt.endsWith(".pdf")){
                pdfUrl = lnktxt
            }else{
                status.text = "Invalid Link Provided, Downloading default PDF...."
            }
            status.text = status.text.toString() + "\nDownloading..."

            //Launch a coroutine in the LifecycleScope
            lifecycleScope.launch(Dispatchers.IO){
                val pdffile = downloadPdf(pdfUrl)
                if(pdffile != null){
                    withContext(Dispatchers.Main){
                        status.text = "PDF Downloaded, Rendering..."
                    }
                    delay(1000)
                    val pdfBitmap = renderPdf(pdffile)

                    pdfBitmap?.let{
                        withContext(Dispatchers.Main){
                            view.setImageBitmap(pdfBitmap)
                            status.text = "PDF rendered"
                        }
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        status.text = "Failed to Download PDF"
                    }
                }
            }
        }

        return retv
    }

    private suspend fun downloadPdf(url: String): File? {
        return withContext(Dispatchers.IO){
            val req = Request.Builder().url(url).build()
            val res = client.newCall(req).execute()
            if(res.isSuccessful){
                val pdfFile = File(activity?.cacheDir, "downloaded_pdf.pdf")
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

    private suspend fun renderPdf(file: File): Bitmap? {
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
}