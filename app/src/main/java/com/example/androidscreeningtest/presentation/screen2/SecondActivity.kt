package com.example.androidscreeningtest.presentation.screen2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.R
import com.example.androidscreeningtest.databinding.ActivitySecondBinding
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    val TAG = "Second Activity"
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySecondBinding
    var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromIntent()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (intent.extras != null) {
            val bundle = intent.extras
            username = bundle?.getString("username_saved").toString()
            Logger.e(TAG, "username_saved $username")
            tv_name.text = username
        }

        val navController = findNavController(R.id.navHostFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun getDataFromIntent() {
        if (intent.extras != null) {
            val bundle = intent.extras
            username = bundle?.getString("username_saved").toString()
        }
    }
}