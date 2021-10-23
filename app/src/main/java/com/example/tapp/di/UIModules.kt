package com.example.tapp.di


import com.example.tapp.ui.BaseViewModel
import com.example.tapp.ui.home.HomeViewModel
import com.example.tapp.ui.SplashViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
val mainModule = module {
	viewModel { BaseViewModel(get()) }
	viewModel { SplashViewModel(get()) }
	viewModel { HomeViewModel(get()) }
}
