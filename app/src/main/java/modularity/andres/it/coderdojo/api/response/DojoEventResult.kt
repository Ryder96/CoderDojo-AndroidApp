package modularity.andres.it.coderdojo.api.response

/**
 * Created by garu on 14/03/18.
 */

data class DojoEventResult(
        val count: Int,
        val events: List<DojoEvent>
)