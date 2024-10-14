package com.example.learning226.storageoptions.sqllitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDBHelper(context: Context, factory:SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DB_NAME,factory,  DB_VERSION) {
    companion object{
        private const val DB_NAME = "learningDB226"
        private const val DB_VERSION = 1

        const val TABLE_NAME = "Userdata"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COlUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_AGE TEXT,
                $COlUMN_IMAGE BLOB
            );
        """
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addData(Name: String, Age: String, imageByteArray: ByteArray): Long {
        val db = this.writableDatabase

        // Check if the record with the same Name and Age already exists
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_NAME, COLUMN_AGE),
            "$COLUMN_NAME = ? AND $COLUMN_AGE = ?",
            arrayOf(Name, Age),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            cursor.close()
            db.close()
            return -2
        }

        val values = ContentValues()
        values.put(COLUMN_NAME, Name)
        values.put(COLUMN_AGE, Age)
        values.put(COlUMN_IMAGE, imageByteArray)

        val status = db.insert(TABLE_NAME, null, values)
        cursor.close()
        db.close()
        return status
    }


    fun fetchData(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME , null)
    }

    fun fetchDataInRange(startAge: Int, endAge: Int): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_AGE BETWEEN ? AND ?", arrayOf(startAge.toString(), endAge.toString()))
    }

    fun deleteAll(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_NAME)
        db.close()
    }


}