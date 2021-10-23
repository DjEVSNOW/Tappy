package com.example.tapp.di


import com.example.tapp.ui.BaseViewModel
import com.example.tapp.ui.home.HomeViewModel
import com.example.tapp.ui.SplashViewModel
import com.example.tapp.ui.organiser.LocationsViewModel
import com.example.tapp.ui.organiser.OrganiserViewModel
import com.example.tapp.ui.organiser.PlannedTripInfoViewModel
import com.example.tapp.ui.search.SearchViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
val mainModule = module {
	viewModel { BaseViewModel(get()) }
	viewModel { SplashViewModel(get()) }
	viewModel { HomeViewModel(get()) }
	viewModel { SearchViewModel(get()) }
	viewModel { OrganiserViewModel(get()) }
	viewModel { PlannedTripInfoViewModel(get()) }
	viewModel { LocationsViewModel(get()) }
}
