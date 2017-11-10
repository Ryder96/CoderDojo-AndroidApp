package modularity.andres.it.coderdojo.app.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by garu on 10/11/17.
 */

@Module
class AppModule(val app: Application) {
    @Singleton
    @Provides
    fun provideApplication(): Application = app
}
