package modularity.andres.it.coderdojo.ui.list.dagger;

import javax.inject.Singleton;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import modularity.andres.it.coderdojo.MainActivity;
import modularity.andres.it.coderdojo.api.dagger.ApiModule;
import modularity.andres.it.coderdojo.app.dagger.AppModule;

/**
 * Created by garu on 18/11/17.
 */

@Singleton
@Subcomponent(modules = {
        ApiModule.class,
        AppModule.class,
        EventListMvpModule.class
})
public interface EventListActivitySubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}