package modularity.andres.it.coderdojo.gui.event.fragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
* Created by andres on 11/7/17.
*/
class MapFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onMapReady(map: GoogleMap?) {
        mMap = map as GoogleMap

        val sydney = LatLng(43.797605, 11.2367634)
        mMap.addMarker(MarkerOptions().position(sydney).title("CoderDojo Here"))
        val cameraPos = CameraPosition.builder().target(sydney)
                .zoom(17f)
                .bearing(90f)
                .tilt(40f)
                .build()
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos))
    }
}