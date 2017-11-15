package modularity.andres.it.coderdojo.api.model

data class DojoOrganizer(
        val id: String,
        val name: String? = id,
        val platform: String
)