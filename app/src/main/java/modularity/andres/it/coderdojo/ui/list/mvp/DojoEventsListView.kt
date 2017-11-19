package modularity.andres.it.coderdojo.ui.list.mvp

import modularity.andres.it.coderdojo.api.response.DojoEvent

/**
 * Created by garu on 14/11/17.
 */

interface DojoEventsListView {

    fun showEvents(events: List<DojoEvent>)

    fun showError(throwable: Throwable)

}
