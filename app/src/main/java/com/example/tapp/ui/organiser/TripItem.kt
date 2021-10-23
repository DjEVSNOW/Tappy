package com.example.tapp.ui.organiser

import android.view.View
import com.example.tapp.R
import com.example.tapp.model.Trip
import com.example.tapp.utils.dpToPx
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_organiser_trip.view.*

class TripItem(val trip: Trip?): Item<GroupieViewHolder>()
{

    override fun getLayout() : Int = R.layout.item_organiser_trip

    override fun bind(viewHolder : GroupieViewHolder, position : Int)
    {
        viewHolder.itemView.apply {
            if (trip != null) {
                tripTitleTV.text = (trip.name)
            } else {
                tripInfoIcon.setImageResource(R.drawable.ic_baseline_add_24)
                tripTitleTV.text = ("Добавить")
            }
        }
    }

}