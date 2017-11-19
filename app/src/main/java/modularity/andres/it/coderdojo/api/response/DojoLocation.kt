package modularity.andres.it.coderdojo.api.response

import java.io.Serializable

data class DojoLocation(
        val name: String,
        val address: String,
        val city: String,
        val country: String,
        val postalCode: String? = null,
        val latitude: Double? = null,
        val longitude: Double? = null
): Serializable
