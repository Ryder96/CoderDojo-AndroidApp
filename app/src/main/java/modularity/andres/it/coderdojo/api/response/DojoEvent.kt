package modularity.andres.it.coderdojo.api.response

import java.io.Serializable

data class DojoEvent (
        val title: String,
        val description: String,
        val logo: String?,
        val icon: String?,
        val ticketurl: String,
        val starttime: Long,
        val endtime: Long,
        val capacity: Int?,
        val participants: Int?,
        val location: DojoLocation,
        val organizer: DojoOrganizer,
        val free: Boolean = true,
        val price: Float? = null
) : Serializable