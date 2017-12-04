package modularity.andres.it.coderdojo.provider.GPS


import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.google.android.gms.location.LocationListener

/**
 * Created by Andres on 11/30/2017.
 */

class ImplLocationProvider() : LocationProvider {

    private var activity: Activity? = null
    private var locationManager = this.activity?.getSystemService(Context.LOCATION_SERVICE);
    private var locationProvider = LocationManager.NETWORK_PROVIDER

    internal var longitude: Double = 0.0
    internal var latitude: Double = 0.0

    constructor(activity: Activity) : this() {
        this.activity = activity
        setupLocationListener()
    }

    override fun queryPosition(location: Location) {
        longitude = location.longitude
        latitude = location.latitude
        Log.d("LatLon", location.toString())
    }

    private fun setupLocationListener() {
        LocationListener { location ->
            queryPosition(location)
        }
    }

}