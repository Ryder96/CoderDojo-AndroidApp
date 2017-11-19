package modularity.andres.it.coderdojo.ui.detail.mvp

import modularity.andres.it.coderdojo.api.response.DojoEvent

/**
 * Created by garu on 19/11/17.
 */

interface EventDetailView {

    fun showDetail(event: DojoEvent)

    fun showError(error: Throwable)

}