package com.lalitpawar.countersample.viewmodel

import android.content.SharedPreferences
import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lalitpawar.credsampleapp.utils.TimerConst
import java.util.*


class MainViewModel : ViewModel() {

    private val time = MutableLiveData<Long>()
    private val strTime = MutableLiveData<String>()
    private val ONE_SECOND: Long = 1000

    private var mInitialTime: Long = 0
    private var timer: Timer? = null

    internal var timeBuffer: Long = 0
    internal var updateTime = 0L
    internal var miliSec: Long = 0

    private var sec: Int = 0
    private var min: Int = 0
    private var hr: Int = 0
    private var days: Int = 0


    fun startTimer(sharedPref: SharedPreferences) {

        if (sharedPref.contains(TimerConst)) {
            timeBuffer = sharedPref.getLong(TimerConst, 0)
        }

        mInitialTime = SystemClock.uptimeMillis()
        timer = Timer()

        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {

                miliSec = SystemClock.uptimeMillis() - mInitialTime

                updateTime  = timeBuffer + miliSec

                strTime.postValue(getTimerTime())
                time.postValue(updateTime)
            }
        }, ONE_SECOND, ONE_SECOND)


    }

    fun stopTimer(sharedPref: SharedPreferences) {
        sharedPref.edit().putLong(TimerConst, time.value!!).apply()
    }

    fun getTime(): MutableLiveData<String> {
        return strTime
    }

    override fun onCleared() {
        super.onCleared()
        timer!!.cancel()
    }


    fun getTimerTime(): String {

        var strTime: String

        sec = (updateTime / 1000).toInt()

        min = sec / 60

        hr = min / 60

        days = hr / 24

        sec %= 60

        min %= 60

        hr %= 24

        if (days != 0 && hr != 0 && min != 0)
            strTime = "$days d $hr h $min m $sec s"
        else if (hr != 0 && min != 0)
            strTime = "$hr h $min m $sec s"
        else if (min != 0)
            strTime = "$min m $sec s"
        else
            strTime = "$sec s"

        return strTime
    }

}

