package com.example.tapp.utils

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tapp.MainActivity
import com.example.tapp.R
import com.example.tapp.ui.BaseViewModel
import com.google.android.material.snackbar.Snackbar

var currentRunningSnackbar : Snackbar? = null
fun Fragment.showSnackbar(text: String, bottomMargin: Float = 0f) : Snackbar? {
	currentRunningSnackbar?.dismiss()
    val activity = requireActivity() as? MainActivity
    if (activity != null) {
        val snackbar = Snackbar.make(
            activity.findViewById<View>(android.R.id.content),
            text,
            Snackbar.LENGTH_LONG
									)
		snackbar.show()
		currentRunningSnackbar = snackbar
		return snackbar
	}
	return null
}
fun Fragment.hideKeyboard()
{
	val activity = requireActivity() as? MainActivity
	if (activity != null)
	{
		val imm : InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(view!!.windowToken, 0)
		
	}
}
fun Fragment.showErrorInfo(code : Int, text : String) = showSnackbar(activity?.getString(R.string.api_error_template) + "${code}: $text")
fun Fragment.showToast(text : String) = Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()

fun Fragment.getNavigationResult(key : String = "result") =
	findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(key)

fun Fragment.setNavigationResult(key : String = "result", result : String)
{
	findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

fun BaseViewModel.getNavigationResult(key : String = "result") =
	navigator.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(key)

fun BaseViewModel.setNavigationResult(key: String = "result", result: String) {
    navigator.previousBackStackEntry?.savedStateHandle?.set(key, result)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher
                                {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
        
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}