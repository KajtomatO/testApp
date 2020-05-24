package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log.d
import com.google.gson.GsonBuilder

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_product.*
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bigButton.setOnClickListener {
            d("RuntimeLog","button was pressed")
            startActivity(Intent(this,AddProductActivity::class.java))
        }

        button2.setOnClickListener {
            var url = "https://archive.scryfall.com/json/scryfall-default-cards.json"
            d("RuntimeLog", "Will access url: $url")

            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()

            client.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    d("RuntimeLog", "HTTP Request failed")
                }

                override fun onResponse(call: Call, response: Response) {
                    d("RuntimeLog", "HTTP Request success: $response")
                    val stringJsonResponse = response.body?.string()
                    d("RuntimeLog", "Response: $stringJsonResponse")

                   // val gson = GsonBuilder().create()
                   // val cardsObject = gson.fromJson(stringJsonResponse,AddProductActivity.ScryfallResponseAutocomplete::class.java)

                }

            })
        }


    }
}

