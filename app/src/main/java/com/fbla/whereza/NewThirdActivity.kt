package com.fbla.whereza

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.fbla.whereza.databinding.ActivityMainBinding
import com.fbla.whereza.databinding.NewThirdPageBinding
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.util.concurrent.Executors
import javax.security.auth.callback.Callback


class NewThirdActivity : AppCompatActivity() {


    private lateinit var binding: NewThirdPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewThirdPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populatePlaces()

        binding.RecyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = CardAdapter(placeList)
        }
    }

    private fun populatePlaces() {

        val rating_selec = intent.getStringExtra("rating").toString()
        val topic_selec = intent.getStringExtra("topic").toString()
        val location_selec = intent.getStringExtra("location").toString()
        val openRightNow_selec = intent.getStringExtra("open").toString()
        val miles_selec = intent.getStringExtra("miles").toString()
        Executors.newSingleThreadExecutor().execute {
            val coordinates =
                URL("https://maps.googleapis.com/maps/api/geocode/json?address=$location_selec&key=AIzaSyBo7DO3CnEOj4zjcdSOq2Q-bEc59EHwxLs").readText()
//            val coordinatesJson = JSONObject(coordinates)
            val cordsPattern = Regex("[-|1-9]+[.]\\d+")

            val latsAndLongs : List<MatchResult> = cordsPattern.findAll(coordinates, 0).toList()
            val latitude = latsAndLongs[0].value
            val longitude = latsAndLongs[1].value



            val ratingExpression = Regex("\\b\\d[.]\\d")
            val rating : Sequence<MatchResult> = cordsPattern.findAll(coordinates, 0)
            rating.forEach()
            {
                    matchResult -> //do stuff and make it find the information and assign it
            }

            val placesListJson =
                URL("https://maps.googleapis.com/maps/api/place/textsearch/json??location=" + latitude + "%" + longitude + "&query=" + topic_selec + "&radius=" + (miles_selec.toDouble() * 1609) + "&opennow=" + openRightNow_selec + "&key=AIzaSyBo7DO3CnEOj4zjcdSOq2Q-bEc59EHwxLs").readText()

            Log.d("JSON", placesListJson)
        }

        glideGet("https://content.fortune.com/wp-content/uploads/2017/04/restuarant-exterior-red-robin-4749x2633.jpg?resize=750,500") { bitmap ->
            val place1 = Places(
                bitmap,
                "Red Robin",
                "5.5 mi.",
                "4.5",
                "200",
                "somewhere you know"
            )
            placeList.add(place1)

            binding.RecyclerView.apply {
                layoutManager = GridLayoutManager(applicationContext, 1)
                adapter = CardAdapter(placeList)
            }
        }
    }




    private fun glideGet(imageUrl: String, callback: (Bitmap?) -> Unit) {
       Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    callback(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
    }
}