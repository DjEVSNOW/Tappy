package com.example.tapp.ui.organiser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tapp.R
import com.example.tapp.databinding.LocationsFragmentBinding
import com.example.tapp.databinding.PlannedTripInfoFragmentBinding
import com.example.tapp.ui.BaseFragment

class LocationsFragment : BaseFragment<LocationsViewModel, LocationsFragmentBinding>(LocationsViewModel::class)  {

    override fun getLayoutRes(): Int  = R.layout.locations_fragment


}