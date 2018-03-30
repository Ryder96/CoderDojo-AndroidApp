package modularity.andres.it.coderdojo.ui.userlocation.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

/**
 * Created by garu on 07/12/17.
 */
class DojoMap(val map: GoogleMap) {

    fun addMarker(position: LatLng, title: String):Marker{
        return this.map.addMarker(
                MarkerOptions()
                .position(position)
                .title(title)
        )
    }

    fun setLocation(position: LatLng, zoom: Float = 10.0F, bearing: Float = 90.0F) {
        val cameraPos = CameraPosition.builder()
                .target(position)
                .zoom(zoom)
                .bearing(bearing)
                .build()
        this.map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos))
    }

    fun clear(){
        this.map.clear()
    }

    fun addCircle(circle: CircleOptions):Circle {
        return this.map.addCircle(circle)
    }


}