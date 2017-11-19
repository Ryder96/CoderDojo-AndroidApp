package modularity.andres.it.coderdojo.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.content_event_details.*
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.ui.detail.fragment.MapFragment
import modularity.andres.it.coderdojo.ui.detail.mvp.EventDetailView

class EventDetailsActivity : AppCompatActivity(), EventDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        setSupportActionBar(toolbar)
        showDetail(intent.extras.get("EVENT") as DojoEvent)
    }

    override fun showDetail(event: DojoEvent) {
        description_container.setOnClickListener(DescriptionListener(this, event.description))

        event.apply {
            toolbar.title = this.title
            toolbar_layout.title = this.title
            event_title.text = this.title
            event_date.text = this.startTime.toString() // TODO Format epoch as string
            event_description.text = this.description

            setupMap() // TODO set map location with event.location#address
        }

    }

    override fun showError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.getMapAsync(mapFragment)

    }


    private class DescriptionListener(var context: AppCompatActivity, var description: CharSequence?) : View.OnClickListener {

        override fun onClick(view: View?) {
            val intent = Intent(context, DescriptionActivity::class.java)
            intent.putExtra("DESC", description.toString())
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)

        }

    }

}
