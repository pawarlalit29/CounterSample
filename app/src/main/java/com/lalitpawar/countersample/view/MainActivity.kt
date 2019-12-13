package com.lalitpawar.countersample.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.lalitpawar.countersample.R
import com.lalitpawar.countersample.databinding.ActivityMainBinding
import com.lalitpawar.countersample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "counterTime"
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        sharedPref = getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        binding.viewmodel = viewModel

    }

    override fun onResume() {
        super.onResume()
        binding.viewmodel?.startTimer(sharedPref)
    }

    override fun onStop() {
        super.onStop()
        binding.viewmodel?.stopTimer(sharedPref)
    }
}
