package com.fbla.whereza

// import all required packages for later use within the app
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// define the class name and its android activity type
class ThirdActivity : AppCompatActivity() {

    // create an event listener for the creation of the app
    override fun onCreate(savedInstanceState: Bundle?) {
        // the code is overridden to run the code of the onCreate event
        // however, it should also add the programming that is in the current function
        super.onCreate(savedInstanceState)
        // the code here is linked to the second_page layout file
        setContentView(R.layout.third_page)

        // this tells the device where to find the UI elements such as the TextView and RatingBar
        val name = findViewById<TextView>(R.id.Name)
        val rating = findViewById<RatingBar>(R.id.Rating)
        val address = findViewById<TextView>(R.id.Address)
        val hours = findViewById<TextView>(R.id.Hours)
        val navigate = findViewById<Button>(R.id.Navigate)
        val wall = findViewById<TextView>(R.id.Wall)

        // all of the values that came from the other Activity are received and assigned to a variable
        val topicSelec = intent.getStringExtra("topic").toString()
        val ratingSelec = intent.getStringExtra("rating").toString()
        val cityStateSelec = intent.getStringExtra("city_state").toString()
        val costSelec = intent.getStringExtra("cost").toString()


        // create a value for the database initializer
        val helper = DBHelper(applicationContext)
        // create a variable for the database to open the cursor with
        val db = helper.readableDatabase

        // define the query to select from the "main" table where all of the values are used as filters
        val query = "SELECT * FROM main WHERE Topic = ? AND Rating = ? AND City_State = ? AND Cost = ?"
        val cursor = db.rawQuery(query , arrayOf(topicSelec, ratingSelec, cityStateSelec, costSelec))

        // the cursor will go to the first entry it can find
        if(cursor!!.moveToFirst()) {
            // the UI elements are assigned a value based on the results from the database
            name.text = cursor.getString(1)
            rating.rating = cursor.getDouble(2).toFloat()
            address.text = cursor.getString(6)
            hours.text = cursor.getString(4)
        }
        // if no attraction is found to match the filters provided by the user...
        else {
            // set a "wall" UI element to become visible and show text that says no match was found
            wall.visibility = View.VISIBLE
            wall.alpha = 1.0f

            // hide all of the main components
            name.alpha = 0.0f
            rating.alpha = 0.0f
            address.alpha = 0.0f
            hours.alpha = 0.0f
            navigate.alpha = 0.0f
        }

        // close the cursor to avoid corruption and use of the database when unnecessary
        cursor.close()

        // when the navigate button is clicked...
        navigate.setOnClickListener {
            // set a value for the url to google maps and add the address of the found attraction
            val url = "http://maps.google.co.in/maps?q=" + address.text

            // create an intent value that sends the user to the site automatically
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            // the user is sent to the google maps page ready to navigate to their desired attraction!
            startActivity(intent)
        }
    }


}