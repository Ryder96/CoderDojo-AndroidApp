package modularity.andres.it.coderdojo.ui.list.dagger

import dagger.Module
import dagger.Provides
import modularity.andres.it.coderdojo.api.DojoApi
import modularity.andres.it.coderdojo.settings.UserPreferences
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListModel
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListModelImpl
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListPresenter
import modularity.andres.it.coderdojo.ui.list.mvp.DojoEventsListView
import javax.inject.Singleton

/**
 * Created by garu on 19/11/17.
 */

@Module
class EventListMvpModule {

    @Singleton
    @Provides
    fun provideEventListModel(api: DojoApi, userPreferences: UserPreferences): DojoEventsListModel =
            DojoEventsListModelImpl(api, userPreferences)

    @Singleton
    @Provides
    fun provideEventListPresenter(model: DojoEventsListModel, view: DojoEventsListView): DojoEventsListPresenter
            = DojoEventsListPresenter(model = model, view = view)

}
