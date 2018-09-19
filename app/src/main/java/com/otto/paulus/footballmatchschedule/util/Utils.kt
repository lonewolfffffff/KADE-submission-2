package com.otto.paulus.footballmatchschedule.util

import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.custom.style
import java.util.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun TextView.normal() {
    typeface = Typeface.DEFAULT
}

fun TextView.bold() {
    typeface = Typeface.DEFAULT_BOLD
}

fun String.formatDate(fromDateFormat:String="dd/MM/yy", toDateFormat:String = "E, dd MMM yyyy") : String {
    val date = SimpleDateFormat(fromDateFormat).parse(this)
    val dateFormatter = SimpleDateFormat(toDateFormat)
    return dateFormatter.format(date)
}