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
import com.example.tapp.ui.BaseFragment

class PlannedTripInfoFragment : BaseFragment<PlannedTripInfoViewModel, PlannedTripInfoFragmentBinding>(PlannedTripInfoViewModel::class) {

    override fun getLayoutRes(): Int = R.layout.planned_trip_info_fragment
}