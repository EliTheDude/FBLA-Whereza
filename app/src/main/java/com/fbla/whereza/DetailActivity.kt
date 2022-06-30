package com.fbla.whereza

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.fbla.whereza.databinding.ActivityMainBinding
import com.fbla.whereza.databinding.FourthPageBinding

class DetailActivity : AppCompatActivity ()
{
    private lateinit var binding: FourthPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FourthPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placeID = intent.getIntExtra(PLACE_ID_EXTRA, -1)
        val place = placeFromID(placeID)
    }

    private fun placeFromID(placeID: Int): Places?
    {
        for(Places in placeList)
        {
            if (Places.id == placeID)
            {
                return Places
            }
        }
        return null
    }
}