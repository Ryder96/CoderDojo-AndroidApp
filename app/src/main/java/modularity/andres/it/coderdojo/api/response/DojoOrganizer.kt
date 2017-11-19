package modularity.andres.it.coderdojo.api.response

import java.io.Serializable

data class DojoOrganizer(
        val id: String,
        val name: String? = id,
        val platform: String
): Serializable