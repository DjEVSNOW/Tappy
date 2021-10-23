package com.example.tapp.ui

import com.example.tapp.R


class SplashFragment : BaseFragment<SplashViewModel>(SplashViewModel::class)
{
	override fun getLayoutRes(): Int = R.layout.splash_fragment
}