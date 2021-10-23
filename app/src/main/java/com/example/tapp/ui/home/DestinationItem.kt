package com.example.tapp.ui.home

import android.view.View
import com.example.tapp.R
import com.example.tapp.model.Destination
import com.example.tapp.utils.dpToPx
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_destination.view.*

class DestinationItem(val destination: Destination): Item<GroupieViewHolder>()
{

    override fun getLayout() : Int = R.layout.item_destination

    override fun bind(viewHolder : GroupieViewHolder, position : Int)
    {
        viewHolder.itemView.apply {
            destinationItemTagsCG.removeAllViews()
            destinationTitleTV.setText(destination.name)
            destination.tags.forEach { tag ->
                val chip = Chip(context)
                chip.text = tag
                destinationItemTagsCG.addView(chip as View)
            }
            destinationPriceTV.text = ("от ${destination.priceRub} ₽")
            Picasso.get()
                .load(destination.logoURL)
                .transform(RoundedCornersTransformation(16.dpToPx, 0))
                .into(destinationImageIV)
        }
    }

}