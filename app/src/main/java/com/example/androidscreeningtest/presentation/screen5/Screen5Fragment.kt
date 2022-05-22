package com.example.androidscreeningtest.presentation.screen5

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Screen5Fragment : Fragment(), GoogleMap.OnMapClickListener {

    private lateinit var map: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        val zoom = 13f
        val monas = LatLng(-6.1770846,106.8310169)
        googleMap.addMarker(MarkerOptions().position(monas).title("Marker in Monas"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(monas))
        googleMap.setMinZoomPreference(zoom)

        googleMap.setOnMapClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_screen5, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.screen5_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list -> {
                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onMapClick(pos: LatLng) {
        Logger.e("Maps", "new pos $pos")
        map.addMarker(MarkerOptions().position(pos).title("$pos"))
        val lat = pos.latitude
        val long = pos.longitude

    }
}