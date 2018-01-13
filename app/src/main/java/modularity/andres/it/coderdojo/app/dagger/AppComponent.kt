package modularity.andres.it.coderdojo.app.dagger

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import modularity.andres.it.coderdojo.app.DojoApp

/**
 * Created by garu on 13/01/18.
 */

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    BindingsModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: DojoApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: DojoApp)
}