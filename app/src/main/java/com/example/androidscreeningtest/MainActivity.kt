package com.example.androidscreeningtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidscreeningtest.databinding.ActivityMainBinding
import com.example.androidscreeningtest.presentation.screen1.Screen1ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
//    private val navController by lazy { (navHostFragment as NavHostFragment).findNavController() }
    private lateinit var mainActivityViewModel: Screen1ViewModel
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.e(TAG, "oncreate")
//        val direction =  NavMainDirections.actionToScreen1Fragment()
//        navController.navigate(direction)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this@MainActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[Screen1ViewModel::class.java]

//        mainActivityViewModel = ViewModelProvider(this)[Screen1ViewModel::class.java]

        btn_next.setOnClickListener {
            Logger.e(TAG, "btn next clicked")
//            val direction =  Screen1FragmentDirections.actionToScreen2Fragment()
//            findNavController().navigate(direction)
        }

        lifecycleScope.launch {
            Logger.e(TAG, "launch viewmodel getlist")
            mainActivityViewModel.getGuestList()
        }

        mainActivityViewModel.resWeatherData?.observe(this, Observer {
            Logger.e(TAG, "mainActivityViewModel observe")

        })
    }
}