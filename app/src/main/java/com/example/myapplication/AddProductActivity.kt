package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.add_product.*
import okhttp3.*
import java.io.IOException

class AddProductActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product)

        recView.layoutManager = LinearLayoutManager(this)


        buttonSearch.setOnClickListener {
            var localCardName = cardName.text
            var url = "https://api.scryfall.com/cards/autocomplete?q=$localCardName"
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

                    //listOfCards.Recycler().
                    val gson = GsonBuilder().create()
                    val cardsObject = gson.fromJson(stringJsonResponse,ScryfallResponseAutocomplete::class.java)


                    runOnUiThread {
                        recView.adapter = MainAdapter(cardsObject)
                    }
                }

            })
        }
    }

class ScryfallResponseAutocomplete(val objec: String, val total_values: Int, val data: List<String> )


}