package modularity.andres.it.coderdojo.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import modularity.andres.it.coderdojo.BuildConfig
import modularity.andres.it.coderdojo.app.dagger.DaggerAppComponent
import modularity.andres.it.coderdojo.app.logging.ProductionLogger
import timber.log.Timber


/**
 * Created by garu on 10/11/17.
 */

class DojoApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        this.initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(ProductionLogger())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }

}
