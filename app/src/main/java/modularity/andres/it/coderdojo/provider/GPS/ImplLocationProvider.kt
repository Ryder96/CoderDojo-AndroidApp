package modularity.andres.it.coderdojo.provider.GPS


import android.Manifest
import android.content.Context
import android.location.Location
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

/**
 * Created by Andres on 11/30/2017.
 */

class ImplLocationProvider : LocationProvider {

    override fun queryPosition(context: Context): Location? {
        val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        var location: Task<Location>? = null
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED) {
            location = mFusedLocationClient.lastLocation.addOnSuccessListener {
                return@addOnSuccessListener
            }
        }
        return location?.result as Location
    }


}