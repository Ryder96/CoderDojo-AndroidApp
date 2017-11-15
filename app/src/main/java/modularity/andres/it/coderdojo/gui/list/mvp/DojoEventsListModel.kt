package modularity.andres.it.coderdojo.gui.list.mvp

import io.reactivex.Single
import modularity.andres.it.coderdojo.api.response.DojoEvent

/**
 * Created by garu on 14/11/17.
 */

interface DojoEventsListModel {
    fun getEvents(latitude: Double, longitude: Double, range: Double = 150.0): Single<List<DojoEvent>>
}
