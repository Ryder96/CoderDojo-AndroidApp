package modularity.andres.it.coderdojo.gui.list.dagger

import dagger.Module
import dagger.Provides
import modularity.andres.it.coderdojo.api.DojoApi
import modularity.andres.it.coderdojo.gui.list.mvp.DojoEventsListModel
import modularity.andres.it.coderdojo.gui.list.mvp.DojoEventsListModelImpl
import modularity.andres.it.coderdojo.gui.list.mvp.DojoEventsListPresenter
import javax.inject.Singleton

/**
 * Created by garu on 19/11/17.
 */

@Module
class EventListMvpModule {

    @Singleton
    @Provides
    fun provideEventListModel(api: DojoApi): DojoEventsListModel = DojoEventsListModelImpl(api)

    @Singleton
    @Provides
    fun provideEventListPresenter(model: DojoEventsListModel): DojoEventsListPresenter
            = DojoEventsListPresenter(model = model, view = null)

}
