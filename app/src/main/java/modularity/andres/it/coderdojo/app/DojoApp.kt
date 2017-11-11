package modularity.andres.it.coderdojo.app

import android.app.Application

import modularity.andres.it.coderdojo.BuildConfig
import modularity.andres.it.coderdojo.app.dagger.AppModule
import modularity.andres.it.coderdojo.app.dagger.DaggerAppComponent
import modularity.andres.it.coderdojo.app.logging.ProductionLogger
import timber.log.Timber

/**
 * Created by garu on 10/11/17.
 */

class DojoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        this.initTimber()
        this.initDagger()
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build().inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        else
            Timber.plant(ProductionLogger())
    }


}
