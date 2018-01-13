package modularity.andres.it.coderdojo.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.content_event_details.*
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.api.response.DojoLocation
import modularity.andres.it.coderdojo.ui.detail.fragment.MapFragment
import modularity.andres.it.coderdojo.ui.detail.mvp.EventDetailView
import timber.log.Timber
import java.util.*

class EventDetailsActivity : AppCompatActivity(), EventDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        setSupportActionBar(toolbar)
        showDetail(intent.extras.get("EVENT") as DojoEvent)

    }

    override fun showDetail(event: DojoEvent) {
        setupDescriptionClick(event.description)
        event.apply {
            toolbar.title = this.title
            toolbar_layout.title = this.title
            event_title.text = this.title
            event_date.text = event.formattedDate()
            event_description.text = this.description
            event_address.text = this.location.address
            setupMap(event.location)
            setupParallax(event)
        }
    }

    private fun setupParallax(event: DojoEvent) {
        Glide.with(this)
                .load(event.logo)
                .apply(RequestOptions().transforms(BlurTransformation(30), CenterCrop()))
                .into(event_parallax)
    }

    private fun setupDescriptionClick(description: String) {
        description_container.setOnClickListener {
            val intent = Intent(this, DescriptionActivity::class.java)
            intent.putExtra("DESC", description)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        }
    }

    override fun showError(error: Throwable) {
        Timber.e(error)
    }

    private fun setupMap(location: DojoLocation) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.setLocation(location)
        mapFragment.getMapAsync(mapFragment)
    }

    private fun DojoEvent.formattedDate() = DateFormat.format("EEEE dd MMMM - hh:mm", Date(this.startTime))

}
