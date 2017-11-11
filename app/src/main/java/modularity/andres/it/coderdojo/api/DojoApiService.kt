package modularity.andres.it.coderdojo.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by garu on 10/11/17.
 */

class DojoApiService(endpoint: String, client: OkHttpClient = OkHttpClient()) {

    val api = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(DojoApi::class.java)

}
