package com.example.tapp.ui.organiser

import android.graphics.Color
import android.view.View
import com.example.tapp.R
import com.example.tapp.model.Accommodation
import com.example.tapp.model.Destination
import com.example.tapp.model.Transfer
import com.example.tapp.utils.dpToPx
import com.example.tapp.utils.getTimeDiff
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_transfer.view.*
import java.util.concurrent.TimeUnit

class AccommodationItem(val accommodation : Accommodation): Item<GroupieViewHolder>()
{

    override fun getLayout() : Int = R.layout.item_transfer

    override fun bind(viewHolder : GroupieViewHolder, position : Int)
    {
        viewHolder.itemView.apply {
            transferTagsCG.removeAllViews()
            val duration = accommodation.checkIn.getTimeDiff(accommodation.checkOut, TimeUnit.HOURS)
            accommodation.tags.forEach { tag ->
                val chip = Chip(context)
                chip.text = tag
                chip.setChipBackgroundColorResource(R.color.bgDark)
                transferTagsCG.addView(chip as View)
            }
            transferDurationTV.text = (accommodation.name)
            trancferPriceTV.text = ("${accommodation.price} ₽")

        }
    }

}