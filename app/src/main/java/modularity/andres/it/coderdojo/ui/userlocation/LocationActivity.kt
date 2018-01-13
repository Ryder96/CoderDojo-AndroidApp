package modularity.andres.it.coderdojo.ui.userlocation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import modularity.andres.it.coderdojo.MainActivity
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.settings.UserPreferences
import modularity.andres.it.coderdojo.ui.userlocation.map.DojoMap
import timber.log.Timber

// TODO This class needs refactor / sub component / mvp
class LocationActivity : AppCompatActivity(), PlaceSelectionListener, OnMapReadyCallback {

    private val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0

    lateinit var map: DojoMap
    lateinit var locationProvider: FusedLocationProviderClient
    var mLocationPermissionGranted: Boolean = false
    var userLocation: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        this.initPlacesAutocomplete()
        this.initLocation()
        this.initMap()
    }

    private fun initLocation() {
        this.locationProvider = LocationServices.getFusedLocationProviderClient(this)
        this.getLocationPermission()
    }

    private fun initPlacesAutocomplete() {
        val autocompleteFragment = fragmentManager.findFragmentById(R.id.location_text) as PlaceAutocompleteFragment
        autocompleteFragment.setOnPlaceSelectedListener(this)
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun locationConfirmed(view: View) {
        if (userLocation != null) {
            val lat: Double = userLocation!!.latitude
            val lon: Double = userLocation!!.longitude
            val userPrefs = UserPreferences(getSharedPreferences(this.packageName, Context.MODE_PRIVATE))
            userPrefs.homeLatitude = lat
            userPrefs.homeLongitude = lon
            userPrefs.available = true
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Select your location first", Toast.LENGTH_SHORT).show() // TODO Show proper error message
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.map = DojoMap(googleMap)
        this.updateMap()
    }

    override fun onPlaceSelected(place: Place) {
        Timber.i(place.address.toString())
        this.userLocationSelected(place.latLng)
    }

    override fun onError(status: Status) {
        Timber.e(status.statusMessage + " - " + status.statusCode)
    }

    private fun userLocationSelected(location: LatLng) {
        Timber.i("User location: ".plus(location.toString()))
        this.map.apply {
            this.clear()
            this.setLocation(location)
            this.addMarker(location, getString(R.string.user_location_marker_title))
        }
        this.userLocation = location
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        mLocationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                mLocationPermissionGranted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                updateMap()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun updateMap() {
        if (mLocationPermissionGranted) {
            map.map.isMyLocationEnabled = true
            map.map.uiSettings.isMyLocationButtonEnabled = true
            getDeviceLocation()
        } else {
            map.map.isMyLocationEnabled = false
            map.map.uiSettings.isMyLocationButtonEnabled = false
        }
    }

    private fun getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                val locationResult = locationProvider.lastLocation
                locationResult.addOnCompleteListener(this, {
                    if (it.isSuccessful && it.result != null)
                        userLocationSelected(LatLng(it.result.latitude, it.result.longitude))
                })
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }

    }
}
