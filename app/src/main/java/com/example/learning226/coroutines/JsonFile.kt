package com.example.learning226.coroutines

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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 * Use the [JsonFile.newInstance] factory method to
 * create an instance of this fragment.
 */
class JsonFile : Fragment() {
    private lateinit var btn: Button
    private lateinit var jsonview: TextView
    private lateinit var status: TextView
    private lateinit var link: EditText
    private val client = OkHttpClient()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_json_file, container, false)

        link = view.findViewById(R.id.coroutinejsonlink)
        btn = view.findViewById(R.id.coroutinejsonbtn)
        status = view.findViewById(R.id.coroutinesjsonstatus)
        jsonview = view.findViewById(R.id.coroutinesjsonview)

        btn.setOnClickListener{
            fetchAndDisplay()
        }

        return view
    }

    private fun fetchAndDisplay(){
        status.text = "Downloading...."

        lifecycleScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                status.text = "Downloaded, Rendering...."
            }
            val jsonUrl = "https://jsonplaceholder.typicode.com/users"
            val jsonData = fetchJsonData(jsonUrl)
            val formattedJson = parseAndFormatJsonData(jsonData)
            withContext(Dispatchers.Main){
                if (jsonData != null){
                    jsonview.text = formattedJson
                    status.text = "Rendered"
                }
                else{
                    status.text = "Failed to Download file"
                }
            }
        }
    }

    private suspend fun fetchJsonData(url: String): String?{
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

    private fun parseAndFormatJsonData(jsonData: String?):String{
        val formattedString = StringBuilder()
        jsonData?.let{
            val jsonArray = JSONArray(it)
            for (i in 0  until  jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                formattedString.append("ID: ${jsonObject.getInt("id")}\n")
                formattedString.append("Name: ${jsonObject.getString("name")}\n")
                formattedString.append("Email: ${jsonObject.getString("email")}\n\n")
            }
        }
        return formattedString.toString()
    }


}