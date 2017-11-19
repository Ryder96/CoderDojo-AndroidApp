package modularity.andres.it.coderdojo.gui.list.dagger

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import modularity.andres.it.coderdojo.gui.list.EventListActivity


/**
 * Created by garu on 15/11/17.
 */

@Module(subcomponents = arrayOf(EventListActivitySubcomponent::class))
abstract class EventListModule {

    @Binds
    @IntoMap
    @ActivityKey(EventListActivity::class)
    internal abstract fun eventListInjectorFactory(builder: EventListActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>

}