package modularity.andres.it.coderdojo.ui.userlocation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_location.*
import modularity.andres.it.coderdojo.MainActivity
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.settings.UserPreferences
import modularity.andres.it.coderdojo.ui.userlocation.map.DojoMap
import timber.log.Timber

// TODO This class needs refactor / sub component / mvp
class LocationActivity : AppCompatActivity(), PlaceSelectionListener, OnMapReadyCallback, SeekBar.OnSeekBarChangeListener, GoogleMap.OnMyLocationButtonClickListener {

    companion object {
        private val CIRCLE_STROKE = Color.parseColor("#EA2195DE")
        private val CIRCLE_FILL = Color.parseColor("#772195DE")
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0
        private const val MIN_DISTANCE = 1
    }

    private lateinit var dojoMap: DojoMap
    private lateinit var locationProvider: FusedLocationProviderClient
    private var locationPermission: Boolean = false
    private var userLocation: LatLng? = null

    private lateinit var userPrefs: UserPreferences

    private var userMarker: Marker? = null
    private var rangeCircle: Circle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        userPrefs = UserPreferences(getSharedPreferences(this.packageName, Context.MODE_PRIVATE))
        this.initPlacesAutocomplete()
        this.initLocation()
        this.initMap()
    }

    private fun initSeekBar() {
        updateRangeText(userPrefs.searchRange)
        this.range_seekbar.progress = userPrefs.searchRange
        this.range_seekbar.setOnSeekBarChangeListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun updateRangeText(value: Int) {
        range_detail.text = value.toString() + " km"
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

    override fun onMapReady(googleMap: GoogleMap) {
        this.dojoMap = DojoMap(googleMap)
        this.dojoMap.map.setOnMyLocationButtonClickListener(this)
        this.updateMap()
        this.initSeekBar()
    }

    fun locationConfirmed(view: View) {
        if (userLocation != null) {
            val lat: Double = userLocation!!.latitude
            val lon: Double = userLocation!!.longitude
            userPrefs.homeLatitude = lat
            userPrefs.homeLongitude = lon
            userPrefs.available = true
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Select your location first", Toast.LENGTH_SHORT).show() // TODO Show proper error message
        }
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
        this.userLocation = location
        this.dojoMap.apply {
            showLocation(location)
            placeUserMarker(location)
            drawCircle(range_seekbar.progress)
        }
    }

    private fun placeUserMarker(location: LatLng) {
        if (userMarker != null) {
            this.userMarker!!.position = location
        } else {
            this.userMarker = this.dojoMap.addMarker(location, getString(R.string.user_location_marker_title))
        }
    }

    private fun DojoMap.showLocation(location: LatLng) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 8.5f - range_seekbar.progress / 100f))
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermission = true
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        locationPermission = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                locationPermission = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                updateMap()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun updateMap() {
        if (locationPermission) {
            dojoMap.map.isMyLocationEnabled = true
            dojoMap.map.uiSettings.isMyLocationButtonEnabled = true
            if (!availableLocation()) {
                getDeviceLocation()
            } else {
                userLocationSelected(LatLng(userPrefs.homeLatitude, userPrefs.homeLongitude))
            }
        } else {
            dojoMap.map.isMyLocationEnabled = false
            dojoMap.map.uiSettings.isMyLocationButtonEnabled = false
        }

    }

    private fun availableLocation(): Boolean {
        return userPrefs.available
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermission) {
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


    private fun drawCircle(progress: Int) {
        if (userLocation != null)
            this.dojoMap.apply {
                if (rangeCircle != null) {
                    rangeCircle!!.center = userLocation
                    rangeCircle!!.radius = progress * 1000.0
                } else {
                    Log.i("Wow", "Add circle")
                    rangeCircle = addCircle(CircleOptions().apply {
                        center(userLocation)
                        radius(progress * 1000.0)
                        strokeColor(CIRCLE_STROKE)
                        fillColor(CIRCLE_FILL)
                        strokeWidth(8f)
                    })
                }
            }

    }


    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        seekBar.progress = if (seekBar.progress < MIN_DISTANCE) MIN_DISTANCE else seekBar.progress
        updateRangeText(seekBar.progress)

        if (this.userLocation != null)
            drawCircle(range_seekbar.progress)

    }


    override fun onStartTrackingTouch(seekBar: SeekBar) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        this.userPrefs.searchRange = range_seekbar.progress
        if (this.userLocation != null) {
            this.dojoMap.addMarker(userLocation!!, getString(R.string.user_location_marker_title))
            this.dojoMap.map.animateCamera(CameraUpdateFactory.newLatLngZoom(this.userLocation, 8.5f - range_seekbar.progress / 100f))
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        getDeviceLocation()
        return true
    }

}
