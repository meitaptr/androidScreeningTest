package com.example.androidscreeningtest.presentation.screen4

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidscreeningtest.App
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.MainViewModelFactory
import com.example.androidscreeningtest.R
import com.example.androidscreeningtest.data.Guest
import kotlinx.android.synthetic.main.fragment_screen4.*
import kotlinx.android.synthetic.main.fragment_screen4_list.*
import kotlinx.coroutines.launch

class Screen4Fragment : Fragment() {
    private lateinit var viewModel: Screen4ViewModel
    val TAG = "Screen4Fragment"

    private val screen4Adapter = Screen4Adapter(
        onItemClicked = { saveClickedGuest(it)},
        data = listOf<Guest>()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = setContentView(this, R.layout.fragment_screen4_list)

    }

    fun saveClickedGuest(guest: Guest) {
        Logger.e(TAG, "save clicked guest")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen4_list, container, false)
//        rv_guest.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = screen4Adapter
//        }
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, 3)
                adapter = screen4Adapter
            }
        }

        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory)[Screen4ViewModel::class.java]

//        lifecycleScope.launch {
//
//        }

        Logger.e(TAG, "launch viewmodel getlist")
        viewModel.getGuestList()

        viewModel.guestList.observe(this.requireActivity()) {
            Logger.e(TAG, "mainActivityViewModel observe")
            Logger.e(TAG, "value list guest $it")
            screen4Adapter.data = it
        }

        return view
    }

    companion object {
        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            Screen4Fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}