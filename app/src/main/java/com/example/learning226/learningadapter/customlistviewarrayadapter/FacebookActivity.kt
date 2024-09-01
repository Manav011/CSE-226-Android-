package com.example.learning226.learningadapter.customlistviewarrayadapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class FacebookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook_desc)

        val openFacebookButton: Button = findViewById(R.id.openFacebookButton)

        openFacebookButton.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"))
            startActivity(facebookIntent)
        }
    }
}
