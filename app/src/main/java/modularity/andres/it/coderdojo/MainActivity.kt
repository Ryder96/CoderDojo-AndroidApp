package modularity.andres.it.coderdojo


import android.Manifest
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.location.LocationServices
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_event_list.*
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.ui.detail.EventDetailsActivity
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListPresenter
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListView
import modularity.andres.it.coderdojo.ui.list.view.EventListAdapter
import modularity.andres.it.coderdojo.ui.settings.SettingsActivity
import java.io.Serializable
import javax.inject.Inject


class MainActivity : AppCompatActivity(), DojoEventsListView, EventListAdapter.EventClickListener {


    @Inject lateinit var presenter: DojoEventsListPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(settings_toolbar)
        syncPosition()
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
        presenter.searchEvents(location!!.latitude, location.longitude, 20.0)
        Log.d("FINISH", location.toString())
    }

    private fun syncPosition() {
        val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED) {
            mFusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
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


}
