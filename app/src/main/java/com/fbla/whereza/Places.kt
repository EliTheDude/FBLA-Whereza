package com.fbla.whereza

import android.graphics.Bitmap

var placeList = mutableListOf<Places>()

val PLACE_ID_EXTRA = "bookExtra"

class Places(
    var placePhoto: Bitmap?,
    var placeName: String,
    var placeRating: String,
    var placeReviews: String,
    var placeAddress: String,
    val id: Int? = placeList.size
)