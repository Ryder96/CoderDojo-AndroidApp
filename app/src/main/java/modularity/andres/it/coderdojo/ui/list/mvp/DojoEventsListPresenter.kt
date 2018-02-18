package modularity.andres.it.coderdojo.ui.list.mvp

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import modularity.andres.it.coderdojo.app.mvp.Presenter

/**
 * Created by garu on 14/11/17.
 */

class DojoEventsListPresenter(model: DojoEventsListModel, view: DojoEventsListView?)
    : Presenter<DojoEventsListModel, DojoEventsListView>(model, view) {

    fun searchEvents(lat: Double, lon: Double, range: Int = 150) {
        model.getEvents(lat, lon, range)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showEvents(it) }, { view?.showError(it) })
    }

    fun searchEvents() {
        if (model.getUserPref().available) {
            model.getEvents(model.getUserPref().homeLatitude, model.getUserPref().homeLongitude, model.getUserPref().searchRange)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ view?.showEvents(it) }, { view?.showError(it) })
        }else{
            view?.requestUserPrefs()
        }
    }

}
