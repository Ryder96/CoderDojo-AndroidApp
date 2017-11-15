package modularity.andres.it.coderdojo.api.model

data class DojoLocation(
        val name: String,
        val address: String,
        val city: String,
        val country: String,
        val postalCode: String? = null,
        val latitude: Double? = null,
        val longitude: Double? = null
)
