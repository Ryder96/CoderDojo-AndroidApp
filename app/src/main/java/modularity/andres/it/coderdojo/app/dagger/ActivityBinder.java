package modularity.andres.it.coderdojo.app.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import modularity.andres.it.coderdojo.MainActivity;

@Module
public abstract class ActivityBinder {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivityInjector();
}
