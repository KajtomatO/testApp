package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_row.view.*


class MainAdapter(val scryfallResponseAutocomplete : AddProductActivity.ScryfallResponseAutocomplete): RecyclerView.Adapter<CustomViewHolder>() {


    // numberOfItems
    override fun getItemCount(): Int {
        return scryfallResponseAutocomplete.total_values
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view?.cardName.text = scryfallResponseAutocomplete.data[position]
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}
