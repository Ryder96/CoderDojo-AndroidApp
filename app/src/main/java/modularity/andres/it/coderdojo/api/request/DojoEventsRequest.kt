package modularity.andres.it.coderdojo.api.request

/**
 * Created by garu on 14/03/18.
 */
data class DojoEventsRequest(
        val lat: Double,
        val lon: Double,
        val range: Int = 150,
        val sorting: String = "distance"
)