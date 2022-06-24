package com.fbla.whereza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.concurrent.schedule
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var fadeIn:Animation
    private lateinit var fadeOut:Animation
    private lateinit var topAnim:Animation
    private lateinit var bottomAnim:Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wherezaLogo = findViewById<ImageView>(R.id.wherezaLogo)
        val wherezaText = findViewById<TextView>(R.id.wherezaText)

        val intent = Intent(this, SecondActivity::class.java)

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)


        wherezaLogo.startAnimation(topAnim)
        wherezaText.startAnimation(bottomAnim)


        Timer().schedule(2000) {
            wherezaLogo.startAnimation(fadeOut)
            wherezaText.startAnimation(fadeOut)
        }

        Timer().schedule(5000) {
            startActivity(intent)
        }

//        wherezaButton.setOnClickListener {
//            wherezaLogo.startAnimation(fadeOut)
//        }

    }

}