package modularity.andres.it.coderdojo.provider.GPS


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

/**
* Created by Andres on 11/30/2017.
*/

class ImplLocationProvider : LocationProvider {
    override fun queryPosition(context: Activity): Location? {
        return null
    }


}