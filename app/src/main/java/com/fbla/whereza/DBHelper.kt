package com.fbla.whereza

// import all required packages for later use within the app
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// This initializes the database and tells the device what the database is called
class DBHelper(context: Context) : SQLiteOpenHelper(context, "information.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}