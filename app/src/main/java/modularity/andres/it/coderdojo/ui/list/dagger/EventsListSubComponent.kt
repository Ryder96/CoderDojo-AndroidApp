package modularity.andres.it.coderdojo.ui.list.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import modularity.andres.it.coderdojo.MainActivity
import modularity.andres.it.coderdojo.api.dagger.ApiModule
import modularity.andres.it.coderdojo.settings.PrefModule
import javax.inject.Singleton

/**
 * Created by garu on 13/01/18.
 */

@Singleton
@Subcomponent(modules = [
    EventListModule::class,
    EventListMvpModule::class,
    ApiModule::class,
    PrefModule::class
])
interface EventsListSubComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

}