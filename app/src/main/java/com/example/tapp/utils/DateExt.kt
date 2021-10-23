package com.example.tapp.utils

import com.example.tapp.data.Consts
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Date.removeTime() : Date {
	val cal = Calendar.getInstance()
	cal.time = this
	cal[Calendar.HOUR_OF_DAY] = 0
	cal[Calendar.MINUTE] = 0
	cal[Calendar.SECOND] = 0
	cal[Calendar.MILLISECOND] = 0
	return cal.time
}
fun Date.getTimeDiff(date2 : Date, timeUnit : TimeUnit) : Long
{
	val diffInMillis = date2.time - time
	return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS)
}
fun DateTime.getTimeDiff(date2 : DateTime, timeUnit : TimeUnit) : Long
{
	val diffInMillis = date2.millis - millis
	return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS)
}
fun Date.getYearsDiff(date2 : Date?) : Int?
{
	val a : Calendar = this.getCalendar()
	val b : Calendar = date2?.getCalendar() ?: this.getCalendar()
	var diff = b[Calendar.YEAR] - a[Calendar.YEAR]
	if (a[Calendar.MONTH] > b[Calendar.MONTH] ||
			a[Calendar.MONTH] == b[Calendar.MONTH] && a[Calendar.DATE] > b[Calendar.DATE])
	{
		diff--
	}
	return diff
}

fun Date.getCalendar() : Calendar
{
	val cal = Calendar.getInstance()
	cal.time = this
	return cal
}

fun Date.addDay(count : Int) : Date
{
	val cal = Calendar.getInstance()
	cal.time = this
	cal.add(Calendar.DAY_OF_MONTH, count)
	this.time = cal.timeInMillis
	return this
}

fun Date.addMonth(count : Int) : Date
{
	val cal = Calendar.getInstance()
	cal.time = this
	cal.add(Calendar.MONTH, count)
	this.time = cal.timeInMillis
	return this
}

fun Date.addYear(count : Int) : Date
{
	val cal = Calendar.getInstance()
	cal.time = this
	cal.add(Calendar.YEAR, count)
	this.time = cal.timeInMillis
	return this
}



fun Date.formatTo(format : String) : String
{
	val sdf = SimpleDateFormat(format)
	return sdf.format(this)
}

fun Date.formatDateToUser() : String
{
	val sdf = SimpleDateFormat(Consts.Formats.DateTime.DATE)
	return sdf.format(this)
}
fun parseDate (inp : String) : Date = SimpleDateFormat(Consts.Formats.DateTime.DATE).parse(inp) ?: Date()
fun parseDateTime (iso : String) : DateTime = DateTime.parse(iso)