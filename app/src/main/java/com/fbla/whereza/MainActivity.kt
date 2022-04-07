package com.fbla.whereza

// import all required packages for later use within the app
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.concurrent.schedule

// define the class name and its android activity type
class MainActivity : AppCompatActivity() {

    //  the variables are initialized as lateinit variables so that they
    //  aren't initialized within the constructor
    private lateinit var fadeIn:Animation
    private lateinit var fadeOut:Animation
    private lateinit var topAnim:Animation
    private lateinit var bottomAnim:Animation

    // create an event listener for the creation of the app
    override fun onCreate(savedInstanceState: Bundle?) {
        // the code is overridden to run the code of the onCreate event
        // however, it should also add the programming that is in the current function
        super.onCreate(savedInstanceState)
        // the code here is linked to the activity_main layout file
        setContentView(R.layout.activity_main)

        // this tells the device where to find the UI elements such as the logo and text
        val wherezaLogo = findViewById<ImageView>(R.id.wherezaLogo)
        val wherezaText = findViewById<TextView>(R.id.wherezaText)

        // an intent variable is created to link the loading page with the main screen
        val intent = Intent(this, SecondActivity::class.java)

        // the fade in and fade out animations are loaded
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        // the top and bottom animations are loaded
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        // both the logo and the text will begin their animations
        wherezaLogo.startAnimation(topAnim)
        wherezaText.startAnimation(bottomAnim)

        // after 2 seconds, the logo and text will begin to fade out
        Timer().schedule(2000) {
            wherezaLogo.startAnimation(fadeOut)
            wherezaText.startAnimation(fadeOut)
        }

        // after the animations are complete, the app switches to the main screen
        Timer().schedule(5000) {
            startActivity(intent)
        }

    }

}