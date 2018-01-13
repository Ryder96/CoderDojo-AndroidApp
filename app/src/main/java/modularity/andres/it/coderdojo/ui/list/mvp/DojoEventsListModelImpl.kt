package modularity.andres.it.coderdojo.ui.list.mvp

import io.reactivex.Single
import modularity.andres.it.coderdojo.api.DojoApi
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.settings.UserPreferences

/**
 * Created by garu on 14/11/17.
 */
class DojoEventsListModelImpl(val api: DojoApi, val userPreferences: UserPreferences) : DojoEventsListModel {

    override fun getUserPref(): UserPreferences = userPreferences

    override fun getEvents(latitude: Double, longitude: Double, range: Double): Single<List<DojoEvent>> =
            api.search(latitude, longitude, range)


}