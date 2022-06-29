package com.fbla.whereza

import `in`.madapps.placesautocomplete.PlaceAPI
import `in`.madapps.placesautocomplete.adapter.PlacesAutoCompleteAdapter
import `in`.madapps.placesautocomplete.listener.OnPlacesDetailsListener
import `in`.madapps.placesautocomplete.model.Address
import `in`.madapps.placesautocomplete.model.Place
import `in`.madapps.placesautocomplete.model.PlaceDetails
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.cosh


class SecondActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")

    var street = ""
    var city = ""
    var state = ""
    var country = ""
    var zipCode = ""

    val placesApi = PlaceAPI.Builder().apiKey("AIzaSyBo7DO3CnEOj4zjcdSOq2Q-bEc59EHwxLs").build(this@SecondActivity)


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_page)


        // val cost_adjectives = listOf("Cheap", "Affordable", "Costly", "Expensive")

        val scrollView = findViewById<ScrollView>(R.id.scrollView)

        val mainLayout = findViewById<ConstraintLayout>(R.id.mainLayout)


        val attractions = findViewById<Spinner>(R.id.attractions)
        val starRating = findViewById<RatingBar>(R.id.ratingBar)
        var location = findViewById<AutoCompleteTextView>(R.id.location)
        val distanceScale = findViewById<SeekBar>(R.id.distanceScale)
        val open = findViewById<Switch>(R.id.open)

        val miles = findViewById<TextView>(R.id.miles)
        val search = findViewById<Button>(R.id.search)

//        val API_Key = BuildConfig.PLACES_API_KEY

        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }


        fun parseAddress(address: ArrayList<Address>) {
            (0 until address.size).forEach { i ->
                when {
                    address[i].type.contains("street_number") -> street += address[i].shortName + " "
                    address[i].type.contains("route") -> street += address[i].shortName
                    address[i].type.contains("locality") -> city = address[i].shortName
                    address[i].type.contains("administrative_area_level_1") -> state = address[i].shortName
                    address[i].type.contains("country") -> country = address[i].shortName
                    address[i].type.contains("postal_code") -> zipCode += address[i].shortName
                }
            }
        }


        fun setupUI(placeDetails: PlaceDetails) {
            val address = placeDetails.address
            parseAddress(address)
            runOnUiThread {
//            streetTextView.text = street
//            location.setText(city + ", " + state)
//            stateTextView.text = state
//            countryTextView.text = country
//            zipCodeTextView.text = zipCode
//            latitudeTextView.text = placeDetails.lat.toString()
//            longitudeTextView.text = placeDetails.lng.toString()
//            placeIdTextView.text = placeDetails.placeId
//            urlTextView.text = placeDetails.url
//            utcOffsetTextView.text = placeDetails.utcOffset.toString()
//            vicinityTextView.text = placeDetails.vicinity
//            compoundCodeTextView.text = placeDetails.compoundPlusCode
//            globalCodeTextView.text = placeDetails.globalPlusCode
            }
        }

        fun getPlaceDetails(placeId: String) {
            placesApi.fetchPlaceDetails(placeId, object :
                OnPlacesDetailsListener {
                override fun onError(errorMessage: String) {
                    Toast.makeText(this@SecondActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onPlaceDetailsFetched(placeDetails: PlaceDetails) {
                    setupUI(placeDetails)
                }
            })
        }

        fun ScrollView.scrollToBottom() {
            val lastChild = getChildAt(childCount - 1)
            val bottom = lastChild.bottom + paddingBottom
            val delta = bottom - (scrollY+ height)
            smoothScrollBy(0, delta)
        }


        location.setAdapter(PlacesAutoCompleteAdapter(this, placesApi))

        location.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val place = parent.getItemAtPosition(position) as Place
                getPlaceDetails(place.id)
                location.setText(place.description)
                location.hideKeyboard()
                location.clearFocus()
            }

        location.setOnFocusChangeListener{ _, hasFocus ->
            if (hasFocus) {
                Log.d("FOCUSISYES", location.isFocused.toString())
                scrollView.smoothScrollTo(0,scrollView.bottom)
            }
        }


        mainLayout.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
//                Log.d("FOCUSISYES", location.isFocused.toString())
                mainLayout.hideKeyboard()
                mainLayout.clearFocus()
            }
        }




//        var selection = findViewById<TextView>(R.id.selected)



        val options = arrayOf("Select an attraction!", "Restaurant", "Hotel", "Theater", "Landmark", "Museum")

        attractions.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)


        distanceScale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                if (distanceScale.progress in 0..50) {

                    miles.text = (distanceScale.progress * .5).toString() + " miles"
                }
                else {
                    if (distanceScale.progress < 0) {
                        distanceScale.progress = 0
                    }
                    else if (distanceScale.progress > 50) {
                        distanceScale.progress = 50
                    }
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        search.setOnClickListener {

            val rating_selec = starRating.rating.toString()
            val topic_selec = attractions.selectedItem.toString()
            val location_selec = location.text.toString()
            val openRightNow_selec = open.isChecked.toString()
            val miles_selec = miles.text.toString().replace((" miles").toRegex(), "")

            val intent = Intent(this, NewThirdActivity::class.java).also {
                it.putExtra("rating", rating_selec)
                it.putExtra("topic", topic_selec)
                it.putExtra("location", location_selec)
                it.putExtra("open", openRightNow_selec)
                it.putExtra("miles", miles_selec)
            }

            startActivity(intent)
        }


//
//        attractions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                selection.text = "Please select an option"
//            }
//
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                selection.text = attractions.selectedItem.toString();
//            }
//        }
    }
}