package com.example.learning226.backgroundprocesses.learningcoroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * A simple [Fragment] subclass.
 * Use the [TextFile.newInstance] factory method to
 * create an instance of this fragment.
 */
class TextFile : Fragment() {

    private lateinit var btn: Button
    private lateinit var txtview: TextView
    private lateinit var status: TextView
    private lateinit var link: EditText
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_text_file, container, false)

        link = view.findViewById(R.id.coroutinetextfilelink)
        btn = view.findViewById(R.id.coroutinetextbtn)
        status = view.findViewById(R.id.coroutinestextstatus)
        txtview = view.findViewById(R.id.coroutinestextview)

        btn.setOnClickListener{
            fetchAndDisplay()
        }

        return view
    }

    private fun fetchAndDisplay(){
        var textUrl = "https://example-files.online-convert.com/document/txt/example.txt"
        var lnktxt = link.text.toString()
        if(lnktxt != "" && lnktxt.endsWith(".txt")){
            textUrl = lnktxt
        }else{
            status.text = "Invalid Link Provided, Downloading default text file..."
        }

        status.text = status.text.toString() + "Downloading..."

        lifecycleScope.launch(Dispatchers.IO){
            val FileContent = fetchTextFile(textUrl)
            withContext(Dispatchers.Main){
                if (FileContent != null){
                    status.text = "Downloaded, Rendering...."
                    delay(1000)

                    txtview.text = FileContent

                    status.text = "Rendered"
                }
                else{
                    status.text = "Failed to Download file"
                }
            }
        }
    }

    private suspend fun fetchTextFile(url: String): String?{
        return withContext(Dispatchers.IO){
            try {
                val req = Request.Builder().url(url).build()
                val res = client.newCall(req).execute()
                if(res.isSuccessful){
                    res.body?.string()
                }
                else{
                    null
                }
            }catch (e: Exception){
                e.printStackTrace()
                null
            }
        }
    }
}