package com.fbla.whereza

var placeList = mutableListOf<Places>()

class Places (
    var placePhoto: String,
    var placeName: String,
    var placeDistance: String,
    var placeRating: String,
    var placeReviews: String,
    var placeAddress: String,
    val id: Int? = placeList.size
)