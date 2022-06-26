package com.fbla.whereza

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.fbla.whereza.databinding.ActivityMainBinding
import java.lang.Exception


class NewThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // val recyclerView = findViewById<ImageView>(R.id.placePhoto)

        populatePlaces()

        val mainActivity = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = CardAdapter(placeList)
        }
    }

    private fun populatePlaces() {
        var bitMapImage: Bitmap? = null
        Glide.with(this)
            .asBitmap()
            .load("https://content.fortune.com/wp-content/uploads/2017/04/restuarant-exterior-red-robin-4749x2633.jpg?resize=750,500")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitMapImage = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
        val place1 = Places(
            bitMapImage,
            "Red Robin",
            "5.5 mi.",
            "4.5",
            "200",
            "somewhere you know"
        )
        placeList.add(place1)
    }
}