package modularity.andres.it.coderdojo.ui.list.mvp

import io.reactivex.Single
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.settings.UserPreferences

/**
 * Created by garu on 14/11/17.
 */

interface DojoEventsListModel {
    fun getEvents(latitude: Double, longitude: Double, range: Int, refresh: Boolean): Single<List<DojoEvent>>

    fun getUserPref(): UserPreferences
}
