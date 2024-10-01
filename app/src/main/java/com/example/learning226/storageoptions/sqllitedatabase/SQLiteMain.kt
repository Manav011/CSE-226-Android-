package com.example.learning226.storageoptions.sqllitedatabase

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class SQLiteMain : AppCompatActivity() {
    private lateinit var addData:Button
    private lateinit var printData:Button
    private lateinit var deleteData:Button
    private lateinit var nameed: EditText
    private lateinit var ageed: EditText
    private lateinit var nametv: TextView
    private lateinit var agetv: TextView

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_main)

        addData = findViewById(R.id.sqliteadddata)
        printData = findViewById(R.id.sqliteprintdata)
        deleteData = findViewById(R.id.sqlitedeletedata)
        nameed = findViewById(R.id.sqllitenameed)
        ageed = findViewById(R.id.sqlliteageed)
        nametv = findViewById(R.id.sqlitenametv)
        agetv = findViewById(R.id.sqliteagetv)

        val db = SQLiteDBHelper(this@SQLiteMain, null)

        addData.setOnClickListener {
            val name = nameed.text.toString().trim()
            val age = ageed.text.toString().trim()

            if (name.isEmpty() || age.isEmpty()) {
                Toast.makeText(this, "Name and age cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                val ageInt = age.toIntOrNull()
                if (ageInt == null || ageInt < 0) {
                    Toast.makeText(this, "Age must be a positive number", Toast.LENGTH_SHORT).show()
                }else{
                    val insertionStatus = db.addData(name, age)

                    if (insertionStatus != -1L) {
                        Toast.makeText(this, "Inserted: $name", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Insertion failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            nameed.text.clear()
            ageed.text.clear()
        }


        printData.setOnClickListener {
            nametv.text = ""
            agetv.text = ""

            val cursor = db.fetchData()

            if (cursor.moveToFirst()) {
                nametv.append(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_NAME)) + "\n")
                agetv.append(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_AGE)) + "\n")

                while (cursor.moveToNext()) {
                    nametv.append(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_NAME)) + "\n")
                    agetv.append(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_AGE)) + "\n")
                }
            } else {
                Toast.makeText(this@SQLiteMain, "No data available", Toast.LENGTH_SHORT).show()
            }

            cursor.close()
        }

        deleteData.setOnClickListener {
            db.deleteAll()
            Toast.makeText(this@SQLiteMain, "Deleting all data....", Toast.LENGTH_SHORT).show()
        }

        db.close()
    }
}