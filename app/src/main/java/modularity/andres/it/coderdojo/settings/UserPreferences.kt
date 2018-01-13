package modularity.andres.it.coderdojo.settings

import android.content.SharedPreferences

/**
 * Created by garu on 07/12/17.
 */

class UserPreferences(prefs: SharedPreferences) {

    var available by prefs.boolean(def = false)

    var homeLatitude by prefs.double()

    var homeLongitude by prefs.double()

    var searchRange by prefs.double(def = 150.0)

}
