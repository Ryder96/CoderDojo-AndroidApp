package modularity.andres.it.coderdojo.api.dagger

import android.content.Context
import com.nytimes.android.external.fs3.FileSystemPersister
import com.nytimes.android.external.fs3.PathResolver
import com.nytimes.android.external.fs3.filesystem.FileSystemFactory
import com.nytimes.android.external.store3.base.impl.MemoryPolicy
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.GsonParserFactory
import dagger.Module
import dagger.Provides
import modularity.andres.it.coderdojo.api.DojoApi
import modularity.andres.it.coderdojo.api.request.DojoEventsRequest
import modularity.andres.it.coderdojo.api.response.DojoEventResult
import okio.BufferedSource
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by garu on 13/03/18.
 */

@Module
class StoreModule {

    @Singleton
    @Provides
    fun provideMemoryPolicy(): MemoryPolicy {
        return MemoryPolicy.MemoryPolicyBuilder()
                .setExpireAfterTimeUnit(TimeUnit.HOURS)
                .setExpireAfterWrite(12)
                .setMemorySize(10)
                .build()
    }

    @Singleton
    @Provides
    fun provideCredentialsPathProvider(): PathResolver<DojoEventsRequest>
            = PathResolver { "${it.lat}_${it.lon}_${it.range}_${it.sorting}" }

    @Singleton
    @Provides
    fun provdeEventsStore(
            context: Context,
            api: DojoApi,
            memoryPolicy: MemoryPolicy,
            pathResolver: PathResolver<DojoEventsRequest>
    ): Store<DojoEventResult, DojoEventsRequest> {
        return StoreBuilder.parsedWithKey<DojoEventsRequest, BufferedSource, DojoEventResult>()
                .persister(FileSystemPersister.create(FileSystemFactory.create(context.filesDir), pathResolver))
                .parser(GsonParserFactory.createSourceParser(DojoEventResult::class.java))
                .memoryPolicy(memoryPolicy)
                .fetcher({ api.searchV2Source(it.lat, it.lon, it.range, it.sorting).map { it.source() } })
                .open()
    }


}
