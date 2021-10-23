package com.example.tapp.ui.organiser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tapp.R
import com.example.tapp.databinding.OrganiserFragmentBinding
import com.example.tapp.databinding.PlannedTripInfoFragmentBinding
import com.example.tapp.model.Trip
import com.example.tapp.ui.BaseFragment
import com.example.tapp.ui.SplashFragmentDirections
import com.example.tapp.ui.search.TransferSelectItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.planned_trip_info_fragment.*

class PlannedTripInfoFragment : BaseFragment<PlannedTripInfoViewModel, PlannedTripInfoFragmentBinding>(PlannedTripInfoViewModel::class) {

    override fun getLayoutRes(): Int = R.layout.planned_trip_info_fragment

    private val adapterTransfers = GroupAdapter<GroupieViewHolder>()
    private val adapterAccommodation = GroupAdapter<GroupieViewHolder>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transferList.adapter = adapterTransfers
        accomondationList.adapter = adapterAccommodation
        viewModel.trip.observeForever { trip -> showInfo(trip) }
        provideBackBtn(backBtn)
        addTransfer.setOnClickListener {
            if (viewModel.trip.value != null)
                viewModel.navigator.navigate(PlannedTripInfoFragmentDirections.actionPlannedTripInfoFragmentToTransfersFragment(viewModel.trip.value!!))
        }
    }
    var price = 0
    fun showInfo(trip : Trip) {
        price = 0
        adapterTransfers.update(trip.transfers.map { transfer ->
            price += transfer.price
            TransferItem(transfer)
        })
        adapterAccommodation.update(trip.accommodations.map { accommodation ->
            price += accommodation.price
            AccommodationItem(accommodation)
        })
        tripNameTV.setText(trip.name)
        totalPriceTV.text = ("$price руб.")

        adapterTransfers.setOnItemClickListener { item, view ->
            if (item is TransferItem){
                trip.transfers.remove(item.transfer)
                price -= item.transfer.price
                viewModel.apiRepository.saveTrip(trip)
                totalPriceTV.text = ("$price руб.")
                adapterTransfers.remove(item)
            }

        }
    }
}