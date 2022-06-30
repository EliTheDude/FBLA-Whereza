package com.fbla.whereza

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fbla.whereza.databinding.CardBinding
import java.net.URL


class CardViewHolder(
    private val cardBinding: CardBinding
) : RecyclerView.ViewHolder(cardBinding.root)
{
    @SuppressLint("SetTextI18n")
    fun bindPlace(place: Places)
    {
        cardBinding.placePhoto.setImageBitmap(place.placePhoto)
        cardBinding.placeName.text = place.placeName
        cardBinding.placeRating.rating = place.placeRating.toFloat()
        cardBinding.placeReviews.text = place.placeRating + " (" + place.placeReviews + ")"
        cardBinding.placeAddress.text = place.placeAddress
    }
}