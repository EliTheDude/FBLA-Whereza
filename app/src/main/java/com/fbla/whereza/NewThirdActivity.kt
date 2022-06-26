package com.fbla.whereza

import android.annotation.SuppressLint
import android.content.Intent
import android.database.CursorIndexOutOfBoundsException
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.lang.Exception


class NewThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_third_page)

        populatePlaces()
    }

    private fun populatePlaces() {
        val place1 = Places(
            "https://www.google.com/",
            "",
            "",
            "",
            "",
            ""
        )
        placeList.add(place1)
    }
}