package modularity.andres.it.coderdojo.api

import io.reactivex.Single
import modularity.andres.it.coderdojo.api.response.DojoEvent
import modularity.andres.it.coderdojo.api.response.DojoEventResult
import okhttp3.ResponseBody
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
            @Query("range") range: Int
    ): Single<List<DojoEvent>>

    @GET("/api/v2/events")
    fun searchV2(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("range") range: Int,
            @Query("sort_by") sorting: String
    ): Single<DojoEventResult>

    @GET("/api/v2/events")
    fun searchV2Source(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("range") range: Int,
            @Query("sort_by") sorting: String
    ): Single<ResponseBody>

}