package modularity.andres.it.coderdojo.gui.adpaters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.gui.event.EventActivity

/**
 * Created by Andres on 15/11/2017.
 */
class EventsListAdapter(private var context: Context, private var eventsList: List<String>) : RecyclerView.Adapter<EventsListAdapter.ViewHolder>() {

    //private lateinit var eventsList : List<DojoEvent>
    //private lateinit var eventsList : List<String>

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_event_list, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        /*
        holder!!.titleView.text = eventsList[position].title
        holder!!.addressView.text = eventsList[position].location.address
        val date = StringBuffer()
        date.append((eventsList[position].startTime)).append(" ").append(eventsList[position].endTime)
        holder!!.dateView.text = date.toString()
        */
        holder!!.titleView.text = eventsList[position]
        holder.addressView.text = eventsList[position]
        holder.dateView.text = eventsList[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, EventActivity::class.java)
            intent.putExtra("TITLE", eventsList[position])
            /*
            .
            .TODO
            .
             */
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int = eventsList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView = itemView.findViewById<TextView>(R.id.item_title_label)!!
        val addressView = itemView.findViewById<TextView>(R.id.item_address_label)!!
        val dateView = itemView.findViewById<TextView>(R.id.item_date_label)!!

    }


}