package modularity.andres.it.coderdojo.provider.GPS

import android.content.Context
import android.location.Location

/**
 * Created by Andres on 11/30/2017.
 */

interface LocationProvider {

    fun queryPosition(context: Context): Location?

}