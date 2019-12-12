package com.lalitpawar.credsampleapp.utils

import android.content.Context
import android.widget.Toast
import java.util.concurrent.TimeUnit

object Utils {
    @JvmStatic
    fun makeUnknownErrorToast(context: Context, message: Int) {
        Toast.makeText(context, context.getString(message), Toast.LENGTH_LONG)
            .show()
    }

    fun getTimerTime(milliseconds : Long) : String{

        var days = TimeUnit.MILLISECONDS.toDays(milliseconds)
        var hr = TimeUnit.MILLISECONDS.toHours(milliseconds)
        var min = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        var sec = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        println("timeman $sec")

        var strTime = "$days d $hr h $min m $sec s"

        return strTime
    }
}