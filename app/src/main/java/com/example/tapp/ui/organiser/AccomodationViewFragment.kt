package com.example.tapp.ui.organiser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tapp.R
import com.example.tapp.databinding.AccomodationViewFragmentBinding
import com.example.tapp.databinding.LocationsFragmentBinding
import com.example.tapp.ui.BaseFragment

class AccomodationViewFragment : BaseFragment<AccomodationViewViewModel, AccomodationViewFragmentBinding>(AccomodationViewViewModel::class)  {

    override fun getLayoutRes(): Int = R.layout.accomodation_view_fragment

}