package modularity.andres.it.coderdojo


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_event_list.*
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.ui.detail.EventDetailsActivity
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListPresenter
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListView
import modularity.andres.it.coderdojo.ui.list.view.EventListAdapter
import modularity.andres.it.coderdojo.ui.settings.SettingsActivity
import modularity.andres.it.coderdojo.ui.userlocation.LocationActivity
import java.io.Serializable
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), DojoEventsListView, EventListAdapter.EventClickListener {

    @Inject lateinit var presenter: DojoEventsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(settings_toolbar)
        presenter.searchEvents()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> { startActivity(Intent(this, SettingsActivity::class.java)) }
        }
        return true
    }

    override fun requestUserPrefs() {
        startActivity(Intent(this, LocationActivity::class.java))
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
