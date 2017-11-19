package modularity.andres.it.coderdojo.app.dagger

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import modularity.andres.it.coderdojo.api.dagger.ApiModule
import modularity.andres.it.coderdojo.app.DojoApp
import modularity.andres.it.coderdojo.gui.list.dagger.EventListModule


/**
 * Created by garu on 10/11/17.
 */

@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AndroidBindingModule::class,
        EventListModule::class,
        AppModule::class,
        ApiModule::class
))
interface AppComponent : AndroidInjector<DojoApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DojoApp>() {

        @BindsInstance
        abstract fun application(application: DojoApp): Builder

        @BindsInstance
        abstract fun appModule(appModule: AppModule): Builder

    }
}
