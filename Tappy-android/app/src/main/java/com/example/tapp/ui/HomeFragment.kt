package com.example.tapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tapp.R

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class) {

    override fun getLayoutRes(): Int = R.layout.home_fragment

}