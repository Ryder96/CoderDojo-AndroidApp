package modularity.andres.it.coderdojo.gui.list.mvp

import io.reactivex.Single
import modularity.andres.it.coderdojo.api.DojoApi
import modularity.andres.it.coderdojo.api.response.DojoEvent

/**
 * Created by garu on 14/11/17.
 */
class DojoEventsListModelImpl(val api: DojoApi) : DojoEventsListModel {

    override fun getEvents(latitude: Double, longitude: Double, range: Double): Single<List<DojoEvent>> =
            api.search(latitude, longitude, range)

}