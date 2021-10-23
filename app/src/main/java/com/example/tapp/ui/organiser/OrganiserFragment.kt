package com.example.tapp.ui.organiser

import android.os.Bundle
import android.view.View
import com.example.tapp.R
import com.example.tapp.databinding.OrganiserFragmentBinding
import com.example.tapp.model.Trip
import com.example.tapp.ui.BaseFragment
import com.example.tapp.ui.SplashFragmentDirections
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.organiser_fragment.*
import kotlinx.android.synthetic.main.planned_trip_info_fragment.*

class OrganiserFragment : BaseFragment<OrganiserViewModel, OrganiserFragmentBinding>(OrganiserViewModel::class) {

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun getLayoutRes(): Int = R.layout.organiser_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedTrips.observeForever { trips ->
            updateList(trips)
        }
        viewModel.getMyTrips()
        tripsList.adapter = adapter
        adapter.setOnItemClickListener { item, view ->
            if (item is TripItem) {
                if (item.trip != null) {
                    viewModel.navigator.navigate(OrganiserFragmentDirections.actionOrganiserFragmentToPlannedTripInfoFragment(item.trip))
                } else {
                    viewModel.navigator.navigate(OrganiserFragmentDirections.actionOrganiserFragmentToSearchFragment())
                }
            }
        }

    }
    fun updateList(trips : List<Trip>) {
        adapter.update(
            trips.map { trip -> TripItem(trip) }
        )
        adapter.add(TripItem(null))
    }
}