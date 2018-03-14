package modularity.andres.it.coderdojo.ui.list.mvp

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import modularity.andres.it.coderdojo.app.mvp.Presenter

/**
 * Created by garu on 14/11/17.
 */

class DojoEventsListPresenter(model: DojoEventsListModel, view: DojoEventsListView?)
    : Presenter<DojoEventsListModel, DojoEventsListView>(model, view) {

    fun searchEvents(refresh: Boolean = false) {
        if (model.getUserPref().available) {
            model.getEvents(
                    latitude = model.getUserPref().homeLatitude,
                    longitude = model.getUserPref().homeLongitude,
                    range = model.getUserPref().searchRange,
                    refresh = refresh
            ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ view?.showEvents(it) }, { view?.showError(it) })
        } else {
            view?.requestUserPrefs()
        }
    }

}
