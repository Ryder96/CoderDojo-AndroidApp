package modularity.andres.it.coderdojo.gui.event

import android.content.Context
import android.content.Intent
import android.net.Uri
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
        setupMap()
        setupDescription()
    }

    private fun setupDescription() {
        val descText = findViewById<TextView>(R.id.description_text) as TextView
        descText.setOnClickListener(descListener(this, descText.toString()))
        descText.text = limitStringTo(descText.text, 500)
    }

    private fun limitStringTo(text: CharSequence?, range: Int): CharSequence? {
        var string = text.toString()
        string = string.substring(0, range)
        string += "..."
        return string
    }

    private fun setupMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.getMapAsync(mapFragment)

    }

    private class descListener(var context: Context, var description: String) : View.OnClickListener {

        override fun onClick(p0: View?) {
            val intent = Intent(context, DescriptionActivity::class.java)
            intent.data = Uri.parse(description)
            intent.type = toString()
            context.startActivity(intent)
        }

    }
}
