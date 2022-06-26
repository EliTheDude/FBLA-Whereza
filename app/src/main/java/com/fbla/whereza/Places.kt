package com.fbla.whereza

import android.graphics.Bitmap

var placeList = mutableListOf<Places>()

class Places(
    var placePhoto: Bitmap?,
    var placeName: String,
    var placeDistance: String,
    var placeRating: String,
    var placeReviews: String,
    var placeAddress: String,
    val id: Int? = placeList.size
)