package com.example.tapp.ui.search

import android.os.Bundle
import android.view.View
import com.example.tapp.R
import com.example.tapp.databinding.TransfersFragmentBinding
import com.example.tapp.model.Transfer
import com.example.tapp.model.TransferType
import com.example.tapp.model.TravelDocument
import com.example.tapp.model.Trip
import com.example.tapp.ui.BaseFragment
import com.example.tapp.utils.addDay
import com.example.tapp.utils.addHours
import com.example.tapp.utils.showToast
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.transfers_fragment.*
import kotlinx.android.synthetic.main.transfers_fragment.destinationList
import kotlinx.android.synthetic.main.transfers_fragment.destinationTagsCG
import java.util.*

class TransfersFragment : BaseFragment<TransfersViewModel, TransfersFragmentBinding>(
    TransfersViewModel::class) {
    val transfers = listOf(
        Transfer(
            100,
            5000,
            TransferType.PLANE,
            Date().addDay(1),
            Date().addDay(1).addHours(4),
            listOf(
                TravelDocument(
                    0, "ticket.pdf", "Иванов Иван Иванович", "https://"
                )
            ),
            listOf("Завтра", "Ночной перелёт"),
            false,
            "СПБ",
            "Сочи"
        ),
        Transfer(
            101,
            5000,
            TransferType.PLANE,
            Date().addHours(4),
            Date().addHours(8),
            listOf(
                TravelDocument(
                    0, "ticket.pdf", "Иванов Иван Иванович", "https://"
                )
            ),
            listOf("Ближайший", "Ночной перелёт"),
            false,
            "СПБ",
            "Сочи"
        ),

        Transfer(
            102,
            5000,
            TransferType.PLANE,
            Date().addHours(-4),
            Date().addHours(4),
            listOf(
                TravelDocument(
                    0, "ticket.pdf", "Иванов Иван Иванович", "https://"
                )
            ),
            listOf("Активный", "Ночной перелёт"),
            false,
            "СПБ",
            "Сочи"
        ),

        Transfer(
            103,
            5000,
            TransferType.PLANE,
            Date().addDay(30),
            Date().addDay(30).addHours(4),
            listOf(
                TravelDocument(
                    0, "ticket.pdf", "Иванов Иван Иванович", "https://"
                )
            ),
            listOf("Далеко", "Ночной перелёт"),
            false,
            "СПБ",
            "Сочи"
        ),


    )
    lateinit var trip : Trip
    
    lateinit var args : TransfersFragmentArgs
    private val adapter = GroupAdapter<GroupieViewHolder>()
    override fun getLayoutRes(): Int  = R.layout.transfers_fragment
    private fun showTags () {
        binding.destinationTagsCG.removeAllViews()
        viewModel.tags.forEachIndexed {index,  tag ->
            val chip = layoutInflater.inflate(R.layout.layout_chip_choice, binding.destinationTagsCG, false) as Chip
            chip.text = tag
            chip.id = index
            chip.setOnCheckedChangeListener { buttonView, isChecked ->
                updateList()
                val tag = viewModel.tags[index]
                showToast(tag)
                if (isChecked)
                    viewModel.tagsSelected.add(tag)
                else
                    viewModel.tagsSelected.remove(tag)
                updateList()
            }
            destinationTagsCG.addView(chip as View)
            
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        destinationList.adapter = adapter
        args = viewModel.navArgs()
        trip = args.trip
        updateList()
        trip.transfers.forEach { destination ->
            viewModel.tags.addAll(destination.tags)
        }
        adapter.setOnItemClickListener { item, view ->
            if (item is TransferSelectItem){
                if (item.isChosen)
                    trip.transfers.remove(item.transfer)
                else
                    trip.transfers.add(item.transfer)
                viewModel.apiRepository.saveTrip(trip)
            }
            adapter.notifyDataSetChanged()
            updateList()
        }
        showTags ()
    }
    fun updateList() {
        adapter.clear()
        transfers.forEach { transfer ->
            if (viewModel.shouldItemBeShown(transfer)) {
                adapter.add(TransferSelectItem(transfer, trip.transfers.contains(transfer)))
            }
        }
    }
}