package com.example.androidscreeningtest.presentation.screen1

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.R
import kotlinx.android.synthetic.main.fragment_screen1.*
import kotlinx.coroutines.launch

/** 09/05/2022 Created by: meitaptr */
class Screen1Fragment: Fragment(R.layout.fragment_screen1) {
    val TAG = "Screen1Fragment"
//    override val resourceLayout: Int = R.layout.fragment_screen1
    private val viewModel: Screen1ViewModel by viewModels()
//    private val viewModel by viewModel<Screen1ViewModel>()

//    override fun onInitViews() {
//        Logger.e(TAG, "onInitView")
//        lifecycleScope.launch {
//            Logger.e(TAG, "launchhh")
//            viewModel.getGuestList()
//        }
//    }

//    private lateinit var viewModel: Screen1ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.e(TAG, "onCreate")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.e(TAG, "onViewCreated")

//        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application = Application()))[Screen1ViewModel::class.java]

        btn_next.setOnClickListener {
            Logger.e(TAG, "btn next clicked")
            val direction =  Screen1FragmentDirections.actionToScreen2Fragment()
            findNavController().navigate(direction)
        }

        lifecycleScope.launch {
            Logger.e(TAG, "getGuestList launch")
            viewModel.getGuestList()
        }

    }

//    override fun onInitObservers() {
//        Logger.e(TAG, "oninitobserver executed")
//
//        viewModel.gg.observe(viewLifecycleOwner, Observer {
//            Logger.e(TAG, "value list guest $it")
////            when (it.status) {
////                Status.SUCCESS -> updateTemperatureText(it.data!!.name, it.data.temp)
////                Status.ERROR -> showError(it.message!!)
////                Status.LOADING -> showLoading()
////            }
//        })
//    }
//
//    override fun onResumeNav() {
//        TODO("Not yet implemented")
//    }




//    override fun onInitViews() {
//        super.onInitViews()
//        Logger.e(TAG, "onInitView")
//
//        btn_next.setOnClickListener {
//            Logger.e(TAG, "btn next clicked")
//            val direction =  Screen1FragmentDirections.actionToScreen2Fragment()
//            findNavController().navigate(direction)
//        }
//
//    }
//
//    override fun onInitObservers() {
//        super.onInitObservers()
//
//    }
}