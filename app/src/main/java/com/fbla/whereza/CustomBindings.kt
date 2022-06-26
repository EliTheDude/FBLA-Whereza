package com.fbla.whereza

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadimage")
fun ImageView(userImageView: ImageView, imageUri:String) {
    Glide.with(userImageView.context)
        .load(imageUri)
        .into(userImageView)
}