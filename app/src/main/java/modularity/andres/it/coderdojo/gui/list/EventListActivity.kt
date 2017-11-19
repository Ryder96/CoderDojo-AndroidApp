package modularity.andres.it.coderdojo.gui.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_event_list.*
import kotlinx.android.synthetic.main.content_event_list.*
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.gui.list.mvp.DojoEventsListPresenter
import modularity.andres.it.coderdojo.gui.list.mvp.DojoEventsListView
import modularity.andres.it.coderdojo.gui.list.view.EventListAdapter
import javax.inject.Inject

class EventListActivity : AppCompatActivity(), DojoEventsListView {

    @Inject lateinit var presenter: DojoEventsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)
        setSupportActionBar(toolbar)
        presenter.view = this
        presenter.searchEvents(43.768009,11.253165,300.0)

    }

    override fun showEvents(events: List<DojoEvent>) {
        this.events_list.adapter = EventListAdapter(events)
        this.events_list.layoutManager = LinearLayoutManager(this)

    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }

}
