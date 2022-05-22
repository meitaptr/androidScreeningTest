package com.example.androidscreeningtest.presentation.screen2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.R
import com.example.androidscreeningtest.presentation.base.FragmentLifecycle
import com.example.androidscreeningtest.presentation.screen4.Screen4ViewModel
import kotlinx.android.synthetic.main.fragment_screen2.*

/** 09/05/2022 Created by: meitaptr */
class Screen2Fragment: Fragment(R.layout.fragment_screen2) {
    val TAG = "Screen2Fragment"
    //    override val resourceLayout: Int = R.layout.fragment_screen1
    private val viewModel: Screen4ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.e(TAG, "onInitView")

        btn_choose_guest.setOnClickListener {
            Logger.e(TAG, "btn btn_choose_guest clicked")
            findNavController().navigate(R.id.actionToScreen4Fragment)
        }

        btn_choose_event.setOnClickListener {
            findNavController().navigate(R.id.actionToScreen5Fragment)
        }
    }
}