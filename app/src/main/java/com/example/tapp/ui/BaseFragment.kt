package com.example.tapp.ui

import android.graphics.Rect
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.tapp.R
import com.example.tapp.data.ApiEvents
import com.example.tapp.databinding.ActivityMainBinding
import com.example.tapp.utils.showErrorInfo
import com.example.tapp.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass

abstract class BaseFragment<out T : BaseViewModel, K : ViewBinding>(viewModelClass: KClass<T>) : Fragment()
{
	protected val viewModel : T by viewModel(clazz = viewModelClass)
	private var isKeyboardShowing = false
	lateinit var binding : K
	private set
	companion object
	{
		var currentLoadingSnackBar : Snackbar? = null
	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?)
	{
		super.onViewCreated(view, savedInstanceState)
		ViewCompat.setTranslationZ(requireView(), 100f)
		viewModel.navigator = Navigation.findNavController(view)
		viewModel.apiErrorHandler = ApiEvents()
		viewModel.apiErrorHandler!!.onError = { code, message, _ ->
			showErrorInfo(code, message)
		}
		viewModel.apiErrorHandler!!.onLoadStart = {
			if (currentLoadingSnackBar == null)
			{
//				currentLoadingSnackBar?.dismiss()
				currentLoadingSnackBar = showSnackbar(getString(R.string.action_loading))
			}
		}
		viewModel.apiErrorHandler!!.onLoadEnd = {
			currentLoadingSnackBar?.dismiss()
			currentLoadingSnackBar = null
		}
		viewModel.resources = context?.applicationContext?.resources
		viewModel.savedStateHandle = arguments
		viewModel.onShowSnackbar = { msg ->
			showSnackbar(msg)
		}
		viewModel.onCreate()
		
		view.viewTreeObserver?.addOnGlobalLayoutListener {
			val r = Rect()
			view.getWindowVisibleDisplayFrame(r)
			val screenHeight = view.height
			
			// r.bottom is the position above soft keypad or device button.
			// if keypad is shown, the r.bottom is smaller than that before.
			val keypadHeight = screenHeight - r.bottom
			if (keypadHeight > screenHeight * 0.05)
			{ // 0.15 ratio is perhaps enough to determine keypad height.
				// keyboard is opened
				if (!isKeyboardShowing)
				{
					isKeyboardShowing = true
					onKeyboardShowChange(true)
				}
			}
			else
			{
				// keyboard is closed
				if (isKeyboardShowing)
				{
					isKeyboardShowing = false
					onKeyboardShowChange(false)
				}
			}
		}
	}
	
	@LayoutRes
	protected abstract fun getLayoutRes() : Int
	
	override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View?
	{

		val superclass: Type = javaClass.genericSuperclass!!
		val aClass = (superclass as ParameterizedType).actualTypeArguments[1] as Class<*>
		try {
			val method: Method = aClass.getDeclaredMethod(
				"inflate",
				LayoutInflater::class.java,
				ViewGroup::class.java,
				Boolean::class.javaPrimitiveType
			)
			binding = method.invoke(null, layoutInflater, container, false) as K

		} catch (e: Exception) {
			e.printStackTrace()
		}
		return binding.root


	}
	
	open fun onKeyboardShowChange(isShown : Boolean)
	{
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		viewModel.savedStateHandle = arguments
	}
	
	fun provideBackBtn(btn : View)
	{
		btn.setOnClickListener {
			viewModel.goBack()
		}
	}
	override fun setArguments(args: Bundle?) {
		if (args != null) {
			super.setArguments(Bundle(args).apply {
				putBundle("BUNDLE_ARGS", args) // Wrap the arguments as BUNDLE_ARGS
			})
		} else {
			super.setArguments(null)
		}
	}
	open fun onBackPressed() : Boolean{
		return true
	}
	
	override fun onStop()
	{
		super.onStop()
	}
}

