package com.example.androidscreeningtest.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), FragmentLifecycle {
    abstract val resourceLayout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(resourceLayout, container, false)

    override fun onResumeNav() {}

    override fun onInitViews() {}

    override fun onInitObservers() {}

}
