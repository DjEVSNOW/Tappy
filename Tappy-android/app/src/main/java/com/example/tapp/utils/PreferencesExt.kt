package com.example.tapp.utils

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.tapp.model.User
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.tomtom.online.sdk.common.location.LatLng
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


const val KEY_SHOW_ONBOARDING = "show_onboarding"
const val KEY_TOKEN = "token"
const val KEY_INCOMPLETE_EXERCISE = "INCOMPLETE_EXERCISE"

var SharedPreferences.showOnboarding: Boolean
	get() = getBoolean(KEY_SHOW_ONBOARDING, true)
	set(value)
	{
		edit { putBoolean(KEY_SHOW_ONBOARDING, value) }
	}

val SharedPreferences.tokenExists : Boolean
	get() = token != null

var SharedPreferences.token : String?
	get() = getString(KEY_TOKEN, null)
	set(value)
	{
		edit { putString(KEY_TOKEN, value) }
	}


var SharedPreferences.isAccountSet: Boolean
	get() = getBoolean("IS_ACCOUNT_SET", false)
	set(value)
	{
		edit { putBoolean("IS_ACCOUNT_SET", value) }
	}

val gson = GsonBuilder()
//	.setDateFormat(Formats.dateTime)
//	.registerTypeAdapter(Date::class.java, DateDeserializer())
	.create()
var SharedPreferences.user : User?
	get() = gson.fromJson(getString("obj-user-personal", ""), User::class.java)
	set(value)
	{
		edit { putString("obj-user-personal", gson.toJson(value)) }
	}
