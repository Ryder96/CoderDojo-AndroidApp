package modularity.andres.it.coderdojo.gui.event

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import modularity.andres.it.coderdojo.R


class EventActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setupMap()
    }



    private fun setupMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.getMapAsync(mapFragment)
        System.err.println("OnCreate end")
    }
}
