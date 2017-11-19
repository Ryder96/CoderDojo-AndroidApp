package modularity.andres.it.coderdojo.gui.list.view

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.api.response.DojoEvent

/**
 * Created by garu on 19/11/17.
 */

class EventListAdapter(val events: List<DojoEvent>) : RecyclerView.Adapter<EventsListHolder>() {

    override fun onBindViewHolder(holder: EventsListHolder, index: Int) {
        holder.bind(events[index])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsListHolder =
            EventsListHolder(parent.inflate(R.layout.dojo_event_item_row, false))

    override fun getItemCount(): Int = events.count()

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
            LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}