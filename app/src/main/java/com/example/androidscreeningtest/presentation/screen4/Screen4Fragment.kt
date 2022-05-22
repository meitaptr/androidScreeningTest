package com.example.androidscreeningtest.presentation.screen4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.R
import com.example.androidscreeningtest.data.cloud.Guest
import com.example.androidscreeningtest.domain.GuestApi
import com.example.androidscreeningtest.domain.GuestDataRepository
import kotlinx.android.synthetic.main.fragment_screen4_list.*

class Screen4Fragment : Fragment(R.layout.fragment_screen4_list) {
    private lateinit var viewModel: Screen4ViewModel
    val TAG = "Screen4Fragment"
    private val services = GuestApi.create()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView.layoutManager = GridLayoutManager(requireContext(), 2)

        val factory = Screen4ViewModelFactory(GuestDataRepository(services))
        viewModel = ViewModelProviders.of(this, factory)[Screen4ViewModel::class.java]
        viewModel.getGuestList()

        viewModel.guestList.observe(this.requireActivity()) {
            recycleView.adapter = Screen4Adapter(it.listGuest)
            swipeRefreshLayout.isRefreshing = false
        }

        if (swipeRefreshLayout.isRefreshing) {
            viewModel.getGuestList()
        }
    }

    fun saveClickedGuest(guest: Guest) {
        Logger.e(TAG, "save clicked guest")
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            Screen4Fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}