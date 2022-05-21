package com.example.androidscreeningtest

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Screen5Fragment : Fragment(), GoogleMap.OnMapClickListener {

    private lateinit var map: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        map = googleMap

        val zoom = 13f
        val sydney = LatLng(-6.1770846,106.8310169)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Monas"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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
                //findNavController().navigate(R.id.action_back_toEvents)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onMapClick(pos: LatLng) {
        Logger.e("Maps","new pos $pos")

        map.addMarker(MarkerOptions().position(pos).title("$pos"))


    }
}