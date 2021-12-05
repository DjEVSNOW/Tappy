package com.example.tapp.ui

import android.content.SharedPreferences
import com.example.tapp.data.ApiRepository
import com.example.tapp.utils.isAccountSet
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SplashViewModel(private val apiRepository: ApiRepository) : BaseViewModel(apiRepository)
{
	private val preferences by inject(SharedPreferences::class.java)
	override fun onCreate()
	{
		super.onCreate()
		launch {
//			if(!preferences.isAccountSet)
				navigator.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
//			else
//				navigator.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
		}
	}
}