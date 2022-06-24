package com.fbla.whereza

import android.annotation.SuppressLint
import android.content.Intent
import android.database.CursorIndexOutOfBoundsException
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.lang.Exception


class ThirdActivity : AppCompatActivity() {
    @SuppressLint("Range", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_page)

        val Name = findViewById<TextView>(R.id.Name)
        val Rating = findViewById<RatingBar>(R.id.Rating)
        val Address = findViewById<TextView>(R.id.Address)
        val Hours = findViewById<TextView>(R.id.Hours)
        val Navigate = findViewById<Button>(R.id.Navigate)

        val Wall = findViewById<TextView>(R.id.Wall)

//        Wall.visibility = View.GONE


        Log.d("Value:", intent.getStringExtra("EXTRA_TEXT").toString())


        val topic_selec = intent.getStringExtra("topic").toString()
        val rating_selec = intent.getStringExtra("rating").toString()
        val city_state_selec = intent.getStringExtra("city_state").toString()
        val cost_selec = intent.getStringExtra("cost").toString()

        Log.d("Yes_Topic:", topic_selec)
        Log.d("Yes_Rating:", rating_selec)
        Log.d("Yes_City_State:", city_state_selec)
        Log.d("Yes_Cost:", cost_selec)

        val helper = DBHelper(applicationContext)
        val db = helper.readableDatabase
        val query = "SELECT * FROM main WHERE Topic = ? AND Rating = ? AND City_State = ? AND Cost = ?"
        val cursor = db.rawQuery(query , arrayOf(topic_selec, rating_selec, city_state_selec, cost_selec))
//        try {
//            cursor.getString(1)
//            Wall.visibility = View.GONE
//            Name.alpha = 1.0f
//            Rating.alpha = 1.0f
//            Address.alpha = 1.0f
//            Hours.alpha = 1.0f
//            Navigate.alpha = 1.0f
//        }
//        catch (e: CursorIndexOutOfBoundsException) {
//            Log.d("EEEEE", "Help meh")
//            Wall.alpha = 1.0f
//            Name.alpha = 0.0f
//            Rating.alpha = 0.0f
//            Address.alpha = 0.0f
//            Hours.alpha = 0.0f
//            Navigate.alpha = 0.0f
//        }
//        if (cursor.getString(1).isEmpty()) {
//            Name.alpha = 0.0f
//            Rating.alpha = 0.0f
//            Address.alpha = 0.0f
//            Hours.alpha = 0.0f
//            Navigate.alpha = 0.0f
//        }

        if(cursor!!.moveToFirst()) {
            Name.text = cursor.getString(1)
            Rating.rating = cursor.getDouble(2).toFloat()
            Address.text = cursor.getString(6)
            Hours.text = cursor.getString(4)
        }
        else {
            Log.d("EEEEE", "Help meh")
            Wall.visibility = View.VISIBLE
            Wall.alpha = 1.0f
            Name.alpha = 0.0f
            Rating.alpha = 0.0f
            Address.alpha = 0.0f
            Hours.alpha = 0.0f
            Navigate.alpha = 0.0f
        }

        cursor.close()

        Navigate.setOnClickListener {
            val url = "http://maps.google.co.in/maps?q=" + Address.text
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }


}