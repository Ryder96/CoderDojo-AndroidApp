package modularity.andres.it.coderdojo.app.dagger

import dagger.Component
import modularity.andres.it.coderdojo.app.DojoApp
import javax.inject.Singleton

/**
 * Created by garu on 10/11/17.
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class
))
interface AppComponent {
    fun inject(app: DojoApp)
}
