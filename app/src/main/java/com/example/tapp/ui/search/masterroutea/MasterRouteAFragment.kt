package com.example.tapp.ui.search.masterroutea

import com.example.tapp.R
import com.example.tapp.databinding.MasterRouteAFragmentBinding
import com.example.tapp.ui.BaseFragment
import com.example.tapp.ui.search.masterroutea.MasterRouteAViewModel

class MasterRouteAFragment : BaseFragment<MasterRouteAViewModel, MasterRouteAFragmentBinding>(MasterRouteAViewModel::class) {
    override fun getLayoutRes(): Int = R.layout.master_route_a_fragment
}