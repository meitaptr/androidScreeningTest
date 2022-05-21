package com.example.androidscreeningtest.presentation.screen4

import androidx.fragment.app.viewModels
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.R
import com.example.androidscreeningtest.presentation.base.BaseFragment
import com.example.androidscreeningtest.presentation.screen1.Screen1ViewModel

/** 09/05/2022 Created by: meitaptr */
class Screen4Fragment: BaseFragment() {
    val TAG = "Screen1Fragment"
    override val resourceLayout: Int = R.layout.fragment_screen4
    private val viewModel: Screen1ViewModel by viewModels()

    override fun onInitViews() {
        super.onInitViews()
        Logger.e(TAG, "onInitViewa")


    }

    override fun onInitObservers() {
        super.onInitObservers()

    }
}