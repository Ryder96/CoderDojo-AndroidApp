package modularity.andres.it.coderdojo.api.dagger

import dagger.Module
import dagger.Provides
import modularity.andres.it.coderdojo.api.DojoApi
import modularity.andres.it.coderdojo.api.DojoApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by garu on 11/11/17.
 */

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideDojoApiService(@Named("dojo_api_endpoint") endpoint: String, client: OkHttpClient): DojoApiService
            = DojoApiService(endpoint, client)

    @Singleton
    @Provides
    fun provideDojoApi(dojoService: DojoApiService): DojoApi = dojoService.api

    @Provides
    @Named("dojo_api_endpoint")
    fun provideEndpoint(): String = "https://dojo.garu.io/" // TODO Get from build and/or remote config

}
