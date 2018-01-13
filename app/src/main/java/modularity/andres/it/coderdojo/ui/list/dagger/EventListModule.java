package modularity.andres.it.coderdojo.ui.list.dagger;

import dagger.Binds;
import dagger.Module;
import modularity.andres.it.coderdojo.MainActivity;
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListView;

@Module
public abstract class EventListModule {

    @Binds
    abstract DojoEventsListView provideFeatureView(MainActivity mainActivity);

}