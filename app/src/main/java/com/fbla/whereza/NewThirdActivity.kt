package com.fbla.whereza

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.fbla.whereza.databinding.NewThirdPageBinding
import java.net.URL
import java.util.concurrent.Executors


class NewThirdActivity : AppCompatActivity() {


    private lateinit var binding: NewThirdPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        populatePlaces()

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

            Log.d("location", coordinates)

            val latsAndLongs : List<MatchResult> = cordsPattern.findAll(coordinates, 0).toList()
            val latitude = latsAndLongs[0].value
            val longitude = latsAndLongs[1].value

            val distance = URL("https://maps.googleapis.com/maps/api/distancematrix/json?destinations=New%20York%20City%2C%20NY&origins=Washington%2C%20DC&units=imperial&key=AIzaSyBo7DO3CnEOj4zjcdSOq2Q-bEc59EHwxLs")
            // ok
            val placesListJson =
                URL("https://maps.googleapis.com/maps/api/place/textsearch/json??location=" + latitude + "%" + longitude + "&query=\"" + rating_selec + "%star%" + topic_selec + "%near%" + location_selec + "\"&radius=" + (miles_selec.toDouble() * 1609) + "&opennow=" + openRightNow_selec + "&key=AIzaSyBo7DO3CnEOj4zjcdSOq2Q-bEc59EHwxLs").readText()

            Log.d("location", "https://maps.googleapis.com/maps/api/place/textsearch/json??location=" + latitude + "%" + longitude + "&query=\"" + rating_selec + "%star%" + topic_selec + "%near%" + location_selec + "\"&radius=" + (miles_selec.toDouble() * 1609) + "&opennow=" + openRightNow_selec + "&key=AIzaSyBo7DO3CnEOj4zjcdSOq2Q-bEc59EHwxLs")

            val photoReferencePattern = Regex("\"photo_reference\" : \"(.*?)\"")
            val photoReference = photoReferencePattern.findAll(placesListJson, 0).toList()

            val intent = Intent(this, FourthActivity::class.java).also {
                it.putExtra("PlacesList", placesListJson)
            }

            startActivity(intent)

        }

//        Log.d("groups", photoReference!!.joinToString(", "))
//        glideGet("https://maps.googleapis.com/maps/api/place/photo?maxwidth=5184&photoreference=" + photoReference!![0].groupValues[1] + "&maxwidth=600&key=YOUR_API_KEY") { bitmap ->
//            val place1 = Places(
//                bitmap,
//                "Red Robin",
//                "5.5 mi.",
//                "4.5",
//                "200",
//                "somewhere you know"
//            )
//            placeList.add(place1)
//
//            binding.RecyclerView.apply {
//                layoutManager = GridLayoutManager(applicationContext, 1)
//                adapter = CardAdapter(placeList)
//            }
//        }

    }

}