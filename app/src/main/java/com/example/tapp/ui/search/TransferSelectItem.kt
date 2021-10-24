package com.example.tapp.ui.search

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.example.tapp.R
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

class TransferSelectItem(val transfer: Transfer, val isChosen : Boolean): Item<GroupieViewHolder>()
{

    override fun getLayout() : Int = R.layout.item_transfer

    override fun bind(viewHolder : GroupieViewHolder, position : Int)
    {
        viewHolder.itemView.apply {
            transferTagsCG.removeAllViews()
            val duration = transfer.departure.getTimeDiff(transfer.arrival, TimeUnit.HOURS)
            transfer.tags.forEach { tag ->
                val chip = Chip(context)
                chip.text = tag
                chip.setChipBackgroundColorResource(R.color.bgDark)
                transferTagsCG.addView(chip as View)
            }
            transferDurationTV.text = ("В пути $duration ч")
            trancferPriceTV.text = ("${transfer.price} ₽")
            transferRemoveBtn.text = (if (isChosen) "-" else "+")
        }
    }

}