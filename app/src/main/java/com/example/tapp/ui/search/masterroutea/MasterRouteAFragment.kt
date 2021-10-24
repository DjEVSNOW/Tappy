package com.example.tapp.ui.search.masterroutea

import android.os.Bundle
import android.view.View
import com.example.tapp.R
import com.example.tapp.databinding.MasterRouteAFragmentBinding
import com.example.tapp.ui.BaseFragment
import com.example.tapp.ui.search.SearchFragmentDirections
import com.example.tapp.ui.search.masterroutea.MasterRouteAViewModel
import kotlinx.android.synthetic.main.master_route_a_fragment.*

class MasterRouteAFragment : BaseFragment<MasterRouteAViewModel, MasterRouteAFragmentBinding>(MasterRouteAViewModel::class) {
    override fun getLayoutRes(): Int = R.layout.master_route_a_fragment
    
    override fun onViewCreated(view : View, savedInstanceState : Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        
        moveNext.setOnClickListener {
            val trip = viewModel.apiRepository.createTrip()
            viewModel.navigator.navigate(MasterRouteAFragmentDirections.actionMasterRouteButtonToTransfersFragment(trip))
        }
    }
}