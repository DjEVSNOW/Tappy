package com.example.tapp.ui.organiser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tapp.data.ApiRepository
import com.example.tapp.model.Trip
import com.example.tapp.ui.BaseViewModel
import kotlinx.coroutines.launch

class PlannedTripInfoViewModel(private val apiRepository: ApiRepository) : BaseViewModel(apiRepository) {
    lateinit var args : PlannedTripInfoFragmentArgs
    val trip = MutableLiveData<Trip>()

    override fun onCreate() {
        super.onCreate()
        args = navArgs()
        trip.value = args.trip

    }
}