package modularity.andres.it.coderdojo.ui.settings.fragment

import android.os.Bundle
import android.preference.PreferenceFragment

/**
* Created by Andres on 12/5/2017.
*/

class SettingsFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // addPreferencesFromResource(R.xml.pref_settings)
        var seekbar = findPreference("SEEKBAR_RANGE")
    }

}
