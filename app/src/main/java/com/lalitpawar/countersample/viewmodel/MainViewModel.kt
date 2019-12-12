package com.lalitpawar.countersample.viewmodel

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import java.util.concurrent.TimeUnit


class MainViewModel : ViewModel() {

    private val time = MutableLiveData<Long>()
    private val ONE_SECOND : Long = 1000

    private var mInitialTime: Long = 0
    private var timer: Timer? = null


    fun showTime(){

        mInitialTime = SystemClock.elapsedRealtime()
        timer = Timer()

        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue =
                    (SystemClock.elapsedRealtime() - mInitialTime)
                // setValue() cannot be called from a background thread so post to main thread.

                time.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)



    }


    fun getTime() : MutableLiveData<Long>{
        return  time
    }

    override fun onCleared() {
        super.onCleared()
        timer!!.cancel()
    }

}

