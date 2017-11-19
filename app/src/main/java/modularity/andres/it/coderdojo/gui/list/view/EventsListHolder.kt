package modularity.andres.it.coderdojo.gui.list.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dojo_event_item_row.view.*
import modularity.andres.it.coderdojo.api.response.DojoEvent

/**
 * Created by garu on 19/11/17.
 */

class EventsListHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(event: DojoEvent) {

        Glide.with(view)
                .load(event.logo)
                .into(view.dojo_cover)

        view.dojo_title.text = event.title
    }

}