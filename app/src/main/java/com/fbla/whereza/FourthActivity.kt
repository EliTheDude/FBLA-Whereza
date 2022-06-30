package com.fbla.whereza

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.service.autofill.FieldClassification
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.fbla.whereza.databinding.FourthPageBinding
import kotlinx.coroutines.newSingleThreadContext
import java.util.concurrent.atomic.AtomicInteger

class FourthActivity : AppCompatActivity() {

    private lateinit var binding: FourthPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FourthPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Hello", "ok")

        val placesListJson = intent.getStringExtra("PlacesList").toString()

        val photoReferencePattern = Regex("\"photo_reference\" : \"(.*?)\"")
        val photoReferenceList : Sequence<MatchResult> = photoReferencePattern.findAll(placesListJson, 0)

        val placeNamesPattern = Regex("\"name\" : \"(.*?)\"")
        val placeNamesList : List<MatchResult> = placeNamesPattern.findAll(placesListJson, 0).toList()

        val placeAddressPattern = Regex("\"formatted_address\" : \"(.*?)\"")
        val placeAddressList : List<MatchResult> = placeAddressPattern.findAll(placesListJson, 0).toList()

        val placeRatingPattern = Regex("\"rating\" : (.*?),")
        val placeRatingList : List<MatchResult> = placeRatingPattern.findAll(placesListJson, 0).toList()

        val placeRatingCountPattern = Regex("\"user_ratings_total\" : (.*?)\n")
        val placeRatingCountList : List<MatchResult> = placeRatingCountPattern.findAll(placesListJson, 0).toList()

        var count = AtomicInteger(-1)

        //Log.d("places", placeNamesList[1].groupValues.joinToString(", "))
        // forEach loop used to display all the matches
        photoReferenceList.forEach()
        {
                matchResult ->

            val newCount = count.incrementAndGet()

            //Log.d("places", placeNamesList[newCount].groupValues.joinToString(", "))

            glideGet("https://maps.googleapis.com/maps/api/place/photo?maxwidth=5184&photoreference=" + matchResult.groupValues[1] + "&key=AIzaSyBo7DO3CnEOj4zjcdSOq2Q-bEc59EHwxLs") { bitmap ->
                placeList.add(Places(
                    bitmap,
                    placeNamesList[newCount].groupValues[1],
                    placeRatingList[newCount].groupValues[1],
                    placeRatingCountList[newCount].groupValues[1],
                    placeAddressList[newCount].groupValues[1]
                )
                )


            binding.RecyclerView.apply {
                layoutManager = GridLayoutManager(applicationContext, 1)
                adapter = CardAdapter(placeList)
            }
            Log.d("Hello", "ok2")
            }

        }

        binding.RecyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = CardAdapter(placeList)
        }
    }
    private fun glideGet(imageUrl: String, callback: (Bitmap?) -> Unit) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    callback(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
    }

}