package com.example.tapp.ui.organiser

import com.example.tapp.R
import com.example.tapp.databinding.OrganiserFragmentBinding
import com.example.tapp.ui.BaseFragment

class OrganiserFragment : BaseFragment<OrganiserViewModel, OrganiserFragmentBinding>(OrganiserViewModel::class) {

    override fun getLayoutRes(): Int = R.layout.organiser_fragment
}