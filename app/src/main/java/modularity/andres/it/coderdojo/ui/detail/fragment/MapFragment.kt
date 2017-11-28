package modularity.andres.it.coderdojo.ui.detail.fragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.api.response.DojoLocation

/**
* Created by andres on 11/7/17.
*/
class MapFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var location: DojoLocation

    override fun onMapReady(map: GoogleMap?) {
        mMap = map as GoogleMap

        val city = LatLng(location.latitude!!, location.longitude!!)
        mMap.addMarker(MarkerOptions().position(city).title(getString(R.string.mapmark_string)))
        val cameraPos = CameraPosition.builder().target(city)
                .zoom(17f)
                .bearing(90f)
                .tilt(40f)
                .build()
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos))

        mMap.setOnMapClickListener(MapClickListener(this))
    }

    fun setLocation(location:DojoLocation){
        this.location = location
    }

    private class MapClickListener(var context: SupportMapFragment) : GoogleMap.OnMapClickListener {
        override fun onMapClick(latLng: LatLng?) {

        }

    }
}