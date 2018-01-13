package modularity.andres.it.coderdojo.app.dagger;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import modularity.andres.it.coderdojo.MainActivity;
import modularity.andres.it.coderdojo.ui.list.dagger.EventsListSubComponent;


@Module
public abstract class BindingsModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindFeatureActivityInjectorFactory(EventsListSubComponent.Builder builder);

}