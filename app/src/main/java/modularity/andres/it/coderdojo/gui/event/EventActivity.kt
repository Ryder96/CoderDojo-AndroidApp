package modularity.andres.it.coderdojo.gui.event

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import modularity.andres.it.coderdojo.R
import modularity.andres.it.coderdojo.gui.event.fragment.MapFragment


class EventActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setup()
    }

    private fun setup() {
        setupMap()
        setupDescription()
        //setupElements()//
    }

    private fun setupDescription() {
        val descText = findViewById<TextView>(R.id.description_text) as TextView
        descText.setOnClickListener(DescriptionListener(this, descText.text))
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
