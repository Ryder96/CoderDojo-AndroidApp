package modularity.andres.it.coderdojo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import modularity.andres.it.coderdojo.gui.event.EventActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this,EventActivity::class.java)
        startActivity(intent)
    }
}
