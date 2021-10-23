package com.example.tapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.tapp.databinding.ActivityMainBinding
import com.example.tapp.ui.BaseFragment
import com.example.tapp.ui.home.HomeFragment
import com.example.tapp.ui.home.HomeFragmentDirections

class MainActivity : AppCompatActivity()
{
	private val bottomNavBarFragments = listOf(
		R.id.homeFragment,
		R.id.searchFragment,
		R.id.accountFragment
	)
	private val runningFragmentsId = mutableListOf<Int>()
	private lateinit var binding: ActivityMainBinding


	private val navController by lazy {
		(supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
	}

	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		val view = binding.root
		setContentView(view)
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//		setupActionBarWithNavController(navController)
		setupBottomNavBar()
	}

	private fun isDestinationFromNavBar(id : Int) : Boolean = true//bottomNavBarFragments.contains(id)
	fun btmNavBarNavigateTo(id : Int)
	{
		navController.popBackStack(R.id.homeFragment, false)
		when (id)
		{
			R.id.searchFragment -> navController.navigate(
				HomeFragmentDirections.actionHomeFragmentToSearchFragment(),
				NavOptions.Builder().setLaunchSingleTop(true).build()
			)
			R.id.accountFragment -> navController.navigate(
				HomeFragmentDirections.actionHomeFragmentToOrganiserFragment(),
				NavOptions.Builder().setLaunchSingleTop(true).build()
			)

		}
	}

	private fun setupBottomNavBar()
	{
		binding.bottomNavigationView.setupWithNavController(navController)
		binding.bottomNavigationView.setOnNavigationItemSelectedListener {
			if (bottomNavBarFragments.contains(it.itemId))
				btmNavBarNavigateTo(it.itemId)
			else
				NavigationUI.onNavDestinationSelected(it, navController)
			true
		}
		navController.addOnDestinationChangedListener { _, destination, _ ->
			binding.bottomNavigationView.visibility = if (isDestinationFromNavBar(destination.id)) View.VISIBLE else View.GONE
		}
	}
	override fun onBackPressed()
	{
		val baseFragment = (supportFragmentManager.fragments.first().childFragmentManager.fragments.first() as? BaseFragment<*,*>)

		if (baseFragment == null || baseFragment.onBackPressed())
		{

			baseFragment?.id?.let { runningFragmentsId.remove(it) }
			super.onBackPressed()
		}
	}
}