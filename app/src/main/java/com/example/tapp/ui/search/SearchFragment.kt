package com.example.tapp.ui.search

import com.example.tapp.R
import com.example.tapp.databinding.SearchFragmentBinding
import com.example.tapp.ui.BaseFragment

class SearchFragment : BaseFragment<SearchViewModel, SearchFragmentBinding>(SearchViewModel::class) {

    override fun getLayoutRes(): Int = R.layout.home_fragment


}