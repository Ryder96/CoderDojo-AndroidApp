package modularity.andres.it.coderdojo.ui.userlocation.mvp

/**
 * Created by garu on 07/12/17.
 */

interface UserLocationView {
    fun onLocationSelect(location: String)
    fun onLocationError(error: Throwable)
}