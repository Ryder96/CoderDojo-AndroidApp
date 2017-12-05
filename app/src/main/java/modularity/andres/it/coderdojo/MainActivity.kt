package modularity.andres.it.coderdojo


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_event_list.*
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.provider.GPS.ImplLocationProvider
import modularity.andres.it.coderdojo.provider.setting.SettingsProvider
import modularity.andres.it.coderdojo.ui.detail.EventDetailsActivity
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListPresenter
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListView
import modularity.andres.it.coderdojo.ui.list.view.EventListAdapter
import modularity.andres.it.coderdojo.ui.settings.SettingsActivity
import java.io.Serializable
import javax.inject.Inject


class MainActivity : AppCompatActivity(), DojoEventsListView, EventListAdapter.EventClickListener {


    @Inject lateinit var presenter: DojoEventsListPresenter
    private val REQUEST_ACCESS_FINE_LOCATION: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(settings_toolbar)
        setupPermissions()
        setupList()
    }

    private fun setupPermissions() {
        GPSPermission()
    }

    private fun setupList() {
        val range: Double = SettingsProvider(this).retrieveRange()
        val position: LatLng = SettingsProvider(this).retrievePosition()
        if (range < 0.0) {
            syncPosition()
        } else {
            setupEvents(position, range)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event_details, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> {
                toSettings()
            }
        }
        return true
    }

    private fun toSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun setupEvents(location: Location?) {
        presenter.view = this
        presenter.searchEvents(location!!.latitude, location.longitude)
    }

    private fun setupEvents(position: LatLng, range: Double) {
        presenter.view = this
        presenter.searchEvents(position.latitude, position.longitude, range)
    }

    private fun syncPosition() {
        val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED) {
            mFusedLocationClient.lastLocation.addOnSuccessListener { location ->
                setupEvents(location)
            }
        }
    }


    override fun onEventClick(event: DojoEvent) {
        val intent = Intent(this, EventDetailsActivity::class.java)
        intent.putExtra("EVENT", event as Serializable)
        startActivity(intent)
    }


    override fun showEvents(events: List<DojoEvent>) {
        this.events_list.adapter = EventListAdapter(events, this)
        this.events_list.layoutManager = LinearLayoutManager(this)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                }
                return
            }
        }
    }


    private fun GPSPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_ACCESS_FINE_LOCATION)
        }
    }


}
