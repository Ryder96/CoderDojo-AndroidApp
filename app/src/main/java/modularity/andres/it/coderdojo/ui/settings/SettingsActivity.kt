package modularity.andres.it.coderdojo.ui.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import modularity.andres.it.coderdojo.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.findFragmentById(R.id.settings_fragment)
    }
}
