package com.fbla.whereza

// import all required packages for later use within the app
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// define the class name and its android activity type
class SecondActivity : AppCompatActivity() {

    // create an event listener for the creation of the app
    override fun onCreate(savedInstanceState: Bundle?) {
        // the code is overridden to run the code of the onCreate event
        // however, it should also add the programming that is in the current function
        super.onCreate(savedInstanceState)
        // the code here is linked to the second_page layout file
        setContentView(R.layout.second_page)

        // a list of strings is made of the various cost adjectives
        val costAdjectives = listOf("Cheap", "Affordable", "Costly", "Expensive")

        // this tells the device where to find the UI elements such as the Spinner and RatingBar
        val attractions = findViewById<Spinner>(R.id.attractions)
        val starRating = findViewById<RatingBar>(R.id.ratingBar)
        val location = findViewById<EditText>(R.id.city_state)
        val costScale = findViewById<SeekBar>(R.id.costScale)
        val cost = findViewById<TextView>(R.id.cost)
        val search = findViewById<Button>(R.id.search)

        // a string array of the various attractions is created
        val options = arrayOf("Select an attraction!", "Restaurant", "Hotel", "Theater", "Landmark", "Museum")

        // this creates and assigns an adapter that organizes the attractions into a list readable by the Spinner object
        attractions.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)

        // an event listener for the costScale progress bar that waits for any changes/interactions
        costScale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            // this code will only run when progress of the bar has been changed
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                // since the progress has changed, the textView of cost adjectives should be updated
                cost.text = costAdjectives[progress]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        // when the search button is clicked...
        search.setOnClickListener {
            // get all of the values from the current UI elements in string format
            val cityStateSelec = location.text.toString()
            val ratingSelec = starRating.rating.toString()
            val topicSelec = attractions.selectedItem.toString()
            val costSelec = (costScale.progress + 1).toString()

            // create an intent that sends the user to the results page known as ThirdActivity
            val intent = Intent(this, ThirdActivity::class.java).also {
                // add the values that were created above to the intent so that it can be accessed
                // from the other activity
                it.putExtra("rating", ratingSelec)
                it.putExtra("topic", topicSelec)
                it.putExtra("city_state", cityStateSelec)
                it.putExtra("cost", costSelec)
            }

            // send the user to the results page
            startActivity(intent)
        }
    }
}