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
import java.lang.Exception
import javax.security.auth.callback.Callback


class NewThirdActivity : AppCompatActivity() {

    private lateinit var binding: NewThirdPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewThirdPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = findViewById<ImageView>(R.id.imageView)

        populatePlaces(image)

        val mainActivity = this

        binding.RecyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = CardAdapter(placeList)
        }
    }

    private fun populatePlaces(image: ImageView) {
        Log.d("DidIt1", glideGet("https://content.fortune.com/wp-content/uploads/2017/04/restuarant-exterior-red-robin-4749x2633.jpg?resize=750,500", image).toString())

        Glide.with(this)
            .asBitmap()
            .load("https://content.fortune.com/wp-content/uploads/2017/04/restuarant-exterior-red-robin-4749x2633.jpg?resize=750,500")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val place1 = Places(
                        resource,
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

                    Log.d("DidIt56789", placeList.isEmpty().toString())
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
    }




    private fun glideGet(imageUrl: String, image: ImageView): Bitmap? {
       var bitMapImage: Bitmap? = null
       Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitMapImage = resource
                    Log.d("DidIt2", bitMapImage.toString())
                    image.setImageBitmap(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
        Log.d("DidIt3", bitMapImage.toString())
        return bitMapImage
    }
}