package modularity.andres.it.coderdojo.api.dagger

import dagger.Module
import dagger.Provides
import modularity.andres.it.coderdojo.api.DojoApi
import modularity.andres.it.coderdojo.api.DojoApiService
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by garu on 11/11/17.
 */

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient()

    @Singleton
    @Provides
    fun provideDojoApiService(@Named("eventbrite_endpoint") endpoint: String, client: OkHttpClient): DojoApiService
            = DojoApiService(endpoint, client)

    @Singleton
    @Provides
    fun provideDojoApi(dojoService: DojoApiService): DojoApi = dojoService.api

    @Provides
    @Named("eventbrite_endpoint")
    fun provideEndpoint(): String = "http://garu.pizza:8089/" // TODO Get from build and/or remote config

}
