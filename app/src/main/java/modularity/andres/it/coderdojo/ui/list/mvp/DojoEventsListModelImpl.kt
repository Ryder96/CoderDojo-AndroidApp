package modularity.andres.it.coderdojo.ui.list.mvp

import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Single
import modularity.andres.it.coderdojo.api.request.DojoEventsRequest
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.api.response.DojoEventResult
import modularity.andres.it.coderdojo.settings.UserPreferences

/**
 * Created by garu on 14/11/17.
 */
class DojoEventsListModelImpl(
        private val store: Store<DojoEventResult, DojoEventsRequest>,
        private val userPreferences: UserPreferences
) : DojoEventsListModel {

    override fun getUserPref(): UserPreferences = userPreferences

    override fun getEvents(latitude: Double, longitude: Double, range: Int, refresh: Boolean): Single<List<DojoEvent>> {
        val request = DojoEventsRequest(latitude, longitude, range)
        val provider = if (refresh) store.fetch(request) else store.get(request)
        return provider.map { it.events }
    }

}