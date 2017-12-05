package modularity.andres.it.coderdojo.ui.settings

import android.os.Bundle
import android.preference.PreferenceActivity
import modularity.andres.it.coderdojo.R

class SettingsActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
}
