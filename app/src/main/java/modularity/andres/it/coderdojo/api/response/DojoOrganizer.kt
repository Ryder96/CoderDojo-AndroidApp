package modularity.andres.it.coderdojo.api.response

data class DojoOrganizer(
        val id: String,
        val name: String? = id,
        val platform: String
)