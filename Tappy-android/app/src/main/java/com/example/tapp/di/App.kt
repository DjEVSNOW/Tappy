package com.example.tapp.di

import android.app.Application
import android.preference.PreferenceManager
import com.example.tapp.data.ApiRepository
import com.example.tapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Suppress("unused")
class App : Application()
{

	override fun onCreate()
	{
		super.onCreate()
		
		

		val appModule = module {
			single { PreferenceManager.getDefaultSharedPreferences(get()) }
			single { ApiRepository() }

		}

		startKoin {
			androidLogger()
			androidContext(this@App)
			androidFileProperties()
			
			modules(
				appModule,
				mainModule,
				   )
		}
	}

}