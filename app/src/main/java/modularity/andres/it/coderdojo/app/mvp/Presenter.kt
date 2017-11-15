package modularity.andres.it.coderdojo.app.mvp

/**
 * Created by garu on 14/11/17.
 */

open class Presenter<MODEL, VIEW>(var model: MODEL, var view: VIEW? = null)