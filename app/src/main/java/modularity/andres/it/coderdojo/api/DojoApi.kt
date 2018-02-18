package modularity.andres.it.coderdojo.api

import io.reactivex.Single
import modularity.andres.it.coderdojo.api.response.DojoEvent
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by garu on 10/11/17.
 */

interface DojoApi {

    @GET("/api/v1/events")
    fun search(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("range") range: Int = 150
    ): Single<List<DojoEvent>>

}