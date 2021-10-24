package com.example.tapp.ui

import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.*
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import androidx.navigation.NavController
import com.example.tapp.data.ApiEvents
import com.example.tapp.data.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(private val apiRepository: ApiRepository) : ViewModel(), CoroutineScope, LifecycleObserver
{
	override val coroutineContext: CoroutineContext = viewModelScope.coroutineContext
	lateinit var navigator : NavController
	var savedStateHandle: Bundle? = null
//	private val savedStateHandle by inject(SavedStateHandle::class.java)
	var resources : Resources? = null
	lateinit var onShowSnackbar : ((str : String)->Unit)
	open var apiErrorHandler : ApiEvents? = null
		set(value)
		{
			if (value != null)
				apiRepository.eventHandler = value
			field = value
		}

	open fun onCreate(){

	}
	open fun goBack()
	{
		navigator.popBackStack()
	}

	val arguments get() = savedStateHandle


	@MainThread
	inline fun <reified T : NavArgs> navArgs() : T
	{
		return NavArgsLazy(T::class)
		{
			arguments ?: throw IllegalStateException("ViewModel $this has null arguments")

		}.value
	}
	fun showSnackbar(msg : String){
		onShowSnackbar.invoke(msg)
	}
}