package com.example.learning226.customlistviewarrayadapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class CustomListViewArrayAdapterExample : AppCompatActivity(){ // ArrayAdapter has less customization means we'll be overriding less methods
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_list_view_array_adapter_example)
        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Model>()

        list.add(Model("Facebook", "desc", R.drawable.facebook))
        list.add(Model("Skype", "desc", R.drawable.skype))
        list.add(Model("Twitter", "desc", R.drawable.twitter))
        list.add(Model("Whatsapp", "desc", R.drawable.whatsapp))
        list.add(Model("Youtube", "desc", R.drawable.youtube))
        list.add(Model("Instagram", "desc", R.drawable.instagram))
        list.add(Model("LinkedIn", "desc", R.drawable.linkedin))
        list.add(Model("Spotify", "desc", R.drawable.spotify))
        list.add(Model("Telegram", "desc", R.drawable.telegram))
        list.add(Model("Dialer", "desc", R.drawable.phone))



        listView.adapter = MyAdapter(this@CustomListViewArrayAdapterExample, R.layout.listviewrow, list)

        listView.setOnItemClickListener{ parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "Facebook", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@CustomListViewArrayAdapterExample, FacebookActivity::class.java))
            }
            if (position == 1) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "Skype", Toast.LENGTH_LONG).show()
            }
            if (position == 2) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "Twitter", Toast.LENGTH_LONG).show()
            }
            if (position == 3) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "WhatsApp", Toast.LENGTH_LONG).show()
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("whatsapp://send?phone=9782604033")))
            }
            if (position == 4) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "Youtube", Toast.LENGTH_LONG).show()
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")))
            }
            if (position == 5) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=manavbafna05"))
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
//                    startActivity(Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.instagram.com/manavbafna05/")))
                    Toast.makeText(this@CustomListViewArrayAdapterExample, "Instagram", Toast.LENGTH_LONG).show()
                }

            }
            if (position == 6) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "LinkedIn", Toast.LENGTH_LONG).show()
                 startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/manavbafna")))
            }
            if (position == 7) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "Spotify", Toast.LENGTH_LONG).show()
                 startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/track/7ixxyJJJKZdo8bsdWwkaB6")))
            }
            if (position == 8) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "Telegram", Toast.LENGTH_LONG).show()
            }
            if (position == 9) {
                Toast.makeText(this@CustomListViewArrayAdapterExample, "Dialer", Toast.LENGTH_LONG).show()
                 startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:9782604033")))
            }


        }
    }
}