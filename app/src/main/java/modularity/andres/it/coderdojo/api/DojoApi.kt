package modularity.andres.it.coderdojo.api

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by garu on 10/11/17.
 */

interface DojoApi {

    @GET("/api/v3/events")
    fun searchRawSource(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("range") range: Int,
            @Query("sort_by") sorting: String
    ): Single<ResponseBody>

}