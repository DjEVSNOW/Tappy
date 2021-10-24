package com.example.tapp.ui.search

import android.os.Bundle
import android.view.View
import com.example.tapp.R
import com.example.tapp.databinding.SearchFragmentBinding
import com.example.tapp.ui.BaseFragment
import com.example.tapp.ui.SplashFragmentDirections
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : BaseFragment<SearchViewModel, SearchFragmentBinding>(SearchViewModel::class) {

    override fun getLayoutRes(): Int = R.layout.search_fragment
    override fun onViewCreated(view : View, savedInstanceState : Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        masterRouteButton.setOnClickListener {
            viewModel.navigator.navigate(SearchFragmentDirections.actionSearchFragmentToMasterRouteAFragment())
        }
    }
}