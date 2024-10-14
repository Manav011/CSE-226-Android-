package com.example.learning226.storageoptions.sqllitedatabase

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import com.example.learning226.R
import java.io.ByteArrayOutputStream

class SQLiteMain : AppCompatActivity() {
    private lateinit var addData:Button
    private lateinit var printData:Button
    private lateinit var deleteData:Button
    private lateinit var rangeData: Button
    private lateinit var nameed: EditText
    private lateinit var ageed: EditText
    private lateinit var nametv: TextView
    private lateinit var agetv: TextView
    private lateinit var rangest: EditText
    private lateinit var rangeend: EditText
    private lateinit var userimage: ImageView
    private lateinit var tablelayout: TableLayout
    private var selectedImageBitmap: Bitmap? = null

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_main)

        addData = findViewById(R.id.sqliteadddata)
        printData = findViewById(R.id.sqliteprintdata)
        deleteData = findViewById(R.id.sqlitedeletedata)
        rangeData = findViewById(R.id.sqlitefetchrange)
        nameed = findViewById(R.id.sqllitenameed)
        ageed = findViewById(R.id.sqlliteageed)
        nametv = findViewById(R.id.sqlitenametv)
        agetv = findViewById(R.id.sqliteagetv)
        rangest = findViewById(R.id.sqlliterangestarted)
        rangeend = findViewById(R.id.sqlliterangeended)
        userimage = findViewById(R.id.sqlliteuserimage)
        tablelayout = findViewById(R.id.sqlitetableLayout)

        val db = SQLiteDBHelper(this@SQLiteMain, null)

        userimage.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, Companion.IMAGE_PICK_CODE)
        }


        addData.setOnClickListener {
            val name = nameed.text.toString().trim()
            val age = ageed.text.toString().trim()

            if (name.isEmpty() || age.isEmpty() || selectedImageBitmap == null) {
                Toast.makeText(this, "Name, Age, Image cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                val ageInt = age.toIntOrNull()
                if (ageInt == null || ageInt < 0) {
                    Toast.makeText(this, "Invalid Age", Toast.LENGTH_SHORT).show()
                }else{
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    selectedImageBitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                    val imageBytes = byteArrayOutputStream.toByteArray()

                    val insertionStatus = db.addData(name, age, imageBytes)

                    if (insertionStatus == -2L) {
                        Toast.makeText(this, "$name - $age is already present", Toast.LENGTH_SHORT).show()
                    }else if(insertionStatus != -1L){
                        Toast.makeText(this, "Inserted: $name - $age", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Insertion failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            nameed.text.clear()
            ageed.text.clear()
            userimage.setImageResource(R.drawable.profile)
        }


        printData.setOnClickListener {
            // Clear the previous data in the TableLayout
            for (i in tablelayout.childCount - 1 downTo 1) { // Start from 1 to skip the header row
                tablelayout.removeViewAt(i)
            }

            val cursor = db.fetchData()

            if (cursor.moveToFirst()) {
                // Create a header row (optional)
//                val headerRow = TableRow(this)
//                val nameHeader = TextView(this).apply {
//                    text = "Name"
//                    setTypeface(null, Typeface.BOLD)
//                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
//                }
//                val ageHeader = TextView(this).apply {
//                    text = "Age"
//                    setTypeface(null, Typeface.BOLD)
//                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
//                }
//                headerRow.addView(nameHeader)
//                headerRow.addView(ageHeader)
//                tablelayout.addView(headerRow)

                // Add data rows
                do {
                    val name = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_NAME))
                    val age = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_AGE))
                    val userImagePath = cursor.getBlob(cursor.getColumnIndex(SQLiteDBHelper.COlUMN_IMAGE))
                    val userImageBitmap = BitmapFactory.decodeByteArray(userImagePath, 0, userImagePath.size)

                    val row = TableRow(this)
                    val nameTextView = TextView(this).apply {
                        text = name
                        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                        gravity = Gravity.CENTER
                    }
                    val ageTextView = TextView(this).apply {
                        text = age
                        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                        gravity = Gravity.CENTER
                    }
                    val userImageView = ImageView(this).apply {
                        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                        setImageBitmap(userImageBitmap)
                        adjustViewBounds = true
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        layoutParams.width = 50
                        layoutParams.height = 250
                    }

                    row.addView(userImageView)
                    row.addView(nameTextView)
                    row.addView(ageTextView)
                    tablelayout.addView(row)
                } while (cursor.moveToNext())
            } else {
                Toast.makeText(this@SQLiteMain, "No data available", Toast.LENGTH_SHORT).show()
            }

            cursor.close()
        }


        deleteData.setOnClickListener {
            db.deleteAll()
            Toast.makeText(this@SQLiteMain, "Deleting all data....", Toast.LENGTH_SHORT).show()
        }


        rangeData.setOnClickListener {
            val startRange = rangest.text.toString().trim()
            val endRange = rangeend.text.toString().trim()

            if (startRange.isEmpty() || endRange.isEmpty()) {
                Toast.makeText(this, "Both range fields must be filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val startAge = startRange.toIntOrNull()
            val endAge = endRange.toIntOrNull()

            if (startAge == null || endAge == null || startAge < 0 || endAge < 0 || startAge > endAge) {
                Toast.makeText(this, "Invalid age range", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            for (i in tablelayout.childCount - 1 downTo 1) {
                tablelayout.removeViewAt(i)
            }

            val cursor = db.fetchDataInRange(startAge, endAge)

            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_NAME))
                    val age = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_AGE))

                    val row = TableRow(this)
                    val nameTextView = TextView(this).apply {
                        text = name
                        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                        gravity = Gravity.CENTER
                    }
                    val ageTextView = TextView(this).apply {
                        text = age
                        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                        gravity = Gravity.CENTER
                    }

                    row.addView(nameTextView)
                    row.addView(ageTextView)
                    tablelayout.addView(row)
                } while (cursor.moveToNext())
            } else {
                Toast.makeText(this@SQLiteMain, "No data available in this range", Toast.LENGTH_SHORT).show()
            }

            cursor.close()
        }


        db.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val imageUri: Uri? = data?.data
            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
            userimage.setImageBitmap(selectedImageBitmap)
        }
    }
}