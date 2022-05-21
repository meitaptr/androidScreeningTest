package com.example.androidscreeningtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidscreeningtest.databinding.FragmentScreen2newBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Screen2Fragment : Fragment() {

    private var _binding: FragmentScreen2newBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScreen2newBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btEvent.setOnClickListener {
            findNavController().navigate(R.id.action_Event)
        }

        binding.btGuest.setOnClickListener {
            findNavController().navigate(R.id.action_Guest)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}