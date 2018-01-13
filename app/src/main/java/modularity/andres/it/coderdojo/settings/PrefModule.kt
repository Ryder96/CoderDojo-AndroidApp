package modularity.andres.it.coderdojo.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by garu on 06/01/18.
 */

@Module
class PrefModule {

    @Singleton
    @Provides
    fun provideUserPreferences(sharedPreferences: SharedPreferences) = UserPreferences(sharedPreferences)

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences
            = context.getSharedPreferences(context.packageName, MODE_PRIVATE)

}