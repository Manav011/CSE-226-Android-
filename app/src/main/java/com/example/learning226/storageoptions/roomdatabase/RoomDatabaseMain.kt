package com.example.learning226.storageoptions.roomdatabase

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.room.Room
import com.example.learning226.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomDatabaseMain : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var database: ContactDatabase
    private lateinit var addbtn: Button
    private lateinit var displaybtn: Button
    private lateinit var nameed: EditText
    private lateinit var ided: EditText
    private lateinit var phoneed: EditText
    private lateinit var deletebtn: Button
    private var selectedContactId: Long? = null

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database_main)

        database = Room.databaseBuilder(applicationContext,
            ContactDatabase::class.java, "contactDb").build()

        addbtn = findViewById(R.id.roomdbaddbtn)
        displaybtn = findViewById(R.id.roomdbdisplaybtn)
        listView = findViewById(R.id.roomdblistView)
        ided = findViewById(R.id.roomdbided)
        nameed = findViewById(R.id.roomdbnamed)
        phoneed = findViewById(R.id.roomdbmobed)
        deletebtn = findViewById(R.id.roomdbdelbtn)

        addbtn.setOnClickListener {
            // Trim whitespace and check if any fields are empty
            val idd1 = ided.text.toString().trim()
            val name1 = nameed.text.toString().trim()
            val phone1 = phoneed.text.toString().trim()
            // basically we need to extract these Contact details before because the updation is being done in a thread
            // which might happen after clear() is executed which will throw an exception telling that EditText Fields
            // are empty

            if (idd1.isEmpty() || name1.isEmpty() || phone1.isEmpty()) {
                Toast.makeText(this@RoomDatabaseMain, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate ID (should be a number)
            if (!idd1.matches(Regex("\\d+"))) {
                Toast.makeText(this@RoomDatabaseMain, "ID must be a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate Phone (should be a 10-digit number)
            if (!phone1.matches(Regex("^\\d{10}$"))) {
                Toast.makeText(this@RoomDatabaseMain, "Phone number must be a valid 10-digit number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Convert to Long after validation
            val iddLong = idd1.toLong()
            val phoneLong = phone1.toLong()

            // Inserting into the database in a coroutine
            GlobalScope.launch {
                database.ContactDAO().insert(
                    Contact(
                        iddLong,
                        name1,
                        phoneLong
                    )
                )
            }

            // Clear the EditTexts
            ided.text.clear()
            nameed.text.clear()
            phoneed.text.clear()
        }


        displaybtn.setOnClickListener {
            database.ContactDAO().getContact().observe(this@RoomDatabaseMain) { contacts ->
                if (contacts.isEmpty()) {
                    Toast.makeText(this@RoomDatabaseMain, "No contacts to display", Toast.LENGTH_SHORT).show()
                } else {
                    val adapter = RoomDbAdapter(this@RoomDatabaseMain, R.layout.roomdb_list_item, contacts)
                    listView.adapter = adapter
                }
            }
        }

        deletebtn.setOnClickListener {
            AlertDialog.Builder(this@RoomDatabaseMain)
                .setTitle("Delete All Contacts")
                .setMessage("Are you sure you want to delete all contacts?")
                .setPositiveButton("Delete") { dialog, which ->
                    GlobalScope.launch {
                        database.ContactDAO().deleteAll()
                        runOnUiThread {
                            Toast.makeText(this@RoomDatabaseMain, "All contacts deleted", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }



        listView.setOnItemLongClickListener { parent, view, position, id ->
            val view = parent[position]
            val id = view.findViewById<TextView>(R.id.roomdbidtv).text.toString().toLong()
            val name = view.findViewById<TextView>(R.id.roomdbnametv).text.toString()
            val phone = view.findViewById<TextView>(R.id.roomdbmobtv).text.toString().toLong()
            selectedContactId = view.findViewById<TextView>(R.id.roomdbidtv).text.toString().toLong()

            val builder = AlertDialog.Builder(this@RoomDatabaseMain)
            builder.setTitle("Edit")
            val linearLayout = LinearLayout(this@RoomDatabaseMain)
            linearLayout.orientation = LinearLayout.VERTICAL

            val idView = EditText(this@RoomDatabaseMain)
            idView.setText(id.toString())
            linearLayout.addView(idView)

            val nameView = EditText(this@RoomDatabaseMain)
            nameView.text.clear()
            nameView.setText(name)
            linearLayout.addView(nameView)

            val phoneView = EditText(this@RoomDatabaseMain)
            phoneView.text.clear()
            phoneView.setText(phone.toString())
            linearLayout.addView(phoneView)

            builder.setView(linearLayout)

            builder.setPositiveButton("Edit", DialogInterface.OnClickListener{ dialog, which ->
                val updatedName = nameView.text.toString()
                val updatedPhone = phoneView.text.toString().toLong()
                GlobalScope.launch {
                    database.ContactDAO().update(Contact(id,updatedName,updatedPhone))
                }
                Toast.makeText(this@RoomDatabaseMain, "Updated $updatedName $updatedPhone",
                    Toast.LENGTH_SHORT).show()

            })

            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })

            builder.show()

            return@setOnItemLongClickListener true
        }
    }
}