package com.example.tapp.ui.home

import android.view.View
import com.example.tapp.R
import com.example.tapp.model.Destination
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_destination.view.*

class DestinationItem(val destination: Destination): Item<GroupieViewHolder>()
{

    override fun getLayout() : Int = R.layout.item_destination

    override fun bind(viewHolder : GroupieViewHolder, position : Int)
    {
        viewHolder.itemView.apply {
            destinationTitleTV.setText(destination.name)
            destination.tags.forEach { tag ->
                val chip = Chip(context)
                chip.text = tag
                destinationItemTagsCG.addView(chip as View)
            }
            destinationPriceTV.setText(destination.priceRub.toString())
            Picasso.get()
                .load(destination.logoURL)
                .into(destinationImageIV)
        }
    }

}