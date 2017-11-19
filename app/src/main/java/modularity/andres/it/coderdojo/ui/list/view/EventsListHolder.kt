package modularity.andres.it.coderdojo.ui.list.view

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.dojo_event_item_row.view.*
import modularity.andres.it.coderdojo.api.response.DojoEvent
import java.util.*

/**
 * Created by garu on 19/11/17.
 */

class EventsListHolder(val view: View, val listener: EventListAdapter.EventClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(event: DojoEvent) {

        view.apply {

            event_card.setOnClickListener { listener.onEventClick(event) }

            Glide.with(this)
                    .load(event.logo)
                    .apply(RequestOptions().transforms(BlurTransformation(30), CenterCrop()))
                    .into(this.dojo_cover)

            this.dojo_title.text = event.title

            this.dojo_location.text = event.formattedLocation()

            this.dojo_date.text = event.formattedDate()
        }

    }

    private fun DojoEvent.formattedLocation(): String {
        var location = this.location.name
        if (this.location.city != this.location.name)
            location = location.plus("\n").plus(this.location.city)
        return location
    }

    private fun DojoEvent.formattedDate() = DateFormat.format("EEEE dd MMMM", this.startTime.toDate())

    private fun Long.toDate(): Date = Date(this)
}