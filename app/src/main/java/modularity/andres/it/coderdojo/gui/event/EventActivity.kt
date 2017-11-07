package modularity.andres.it.coderdojo.gui.event

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ms.square.android.expandabletextview.ExpandableTextView
import kotlinx.android.synthetic.main.activity_event.*
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.gui.event.fragment.MapFragment


class EventActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setupMap()
    }



    private fun setupMap() {
        val mapFragment =
                supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.getMapAsync(mapFragment)
        System.err.println("OnCreate end")
    }
}
