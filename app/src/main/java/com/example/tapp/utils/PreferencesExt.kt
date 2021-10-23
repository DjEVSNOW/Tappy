package com.example.tapp.utils

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.tapp.data.Consts
import com.example.tapp.model.Trip
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

fun <T> SharedPreferences.putList(spListKey: String, list: List<T>) {
	val type = object : TypeToken<List<T>>() {}.type
	val listJson = Gson().toJson(list, type)
	edit {
		putString(spListKey, listJson)
	}
}

inline fun <reified T> SharedPreferences.getList(spListKey: String): List<T> {
	val listJson = getString(spListKey, "")
	if (!listJson.isNullOrBlank()) {
		val type = object : TypeToken<T>()
		{}.type
		val list = mutableListOf<T>()
		val gson = Gson()
		val arry : JsonArray = JsonParser().parse(listJson).asJsonArray
		for (jsonElement in arry)
		{
			list.add(gson.fromJson(jsonElement, T::class.java))
		}

		return list
//		return Gson().fromJson(listJson, type)
	}
	return listOf()
}

var SharedPreferences.isAccountSet: Boolean
	get() = getBoolean("IS_ACCOUNT_SET", false)
	set(value)
	{
		edit { putBoolean("IS_ACCOUNT_SET", value) }
	}
class DateDeserializer : JsonDeserializer<Date?>
{

	@Throws(JsonParseException::class)
	override fun deserialize(element: JsonElement, arg1: Type?, arg2: JsonDeserializationContext?): Date?
	{
		val date = element.asString
		val format = SimpleDateFormat(Consts.Formats.DateTime.DATE_TIME)
		return try
		{
			format.parse(date)
		} catch (exp : ParseException)
		{
			Log.e("Failed to parse Date:", exp.localizedMessage)
			null
		}
	}
}
val gson = GsonBuilder()
	.registerTypeAdapter(Date::class.java, DateDeserializer())
	.create()
var SharedPreferences.trips : List<Trip>
	get() = getList<Trip>("trips")
	set(value)
	{
		putList("trips",value)
	}
