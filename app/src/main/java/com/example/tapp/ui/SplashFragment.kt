package com.example.tapp.ui

import com.example.tapp.R
import com.example.tapp.databinding.SplashFragmentBinding


class SplashFragment : BaseFragment<SplashViewModel, SplashFragmentBinding>(SplashViewModel::class)
{
	override fun getLayoutRes(): Int = R.layout.splash_fragment
}