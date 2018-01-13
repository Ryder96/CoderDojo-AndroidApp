package modularity.andres.it.coderdojo.app.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import modularity.andres.it.coderdojo.app.DojoApp
import modularity.andres.it.coderdojo.ui.list.dagger.EventsListSubComponent


/**
 * Created by garu on 13/01/18.
 */

@Module(subcomponents = [
    EventsListSubComponent::class
])
class AppModule {

    @Provides
    fun provideContext(application: DojoApp): Context {
        return application.applicationContext
    }

}