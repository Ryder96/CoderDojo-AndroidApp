package modularity.andres.it.coderdojo.app.logging

import android.util.Log

import timber.log.Timber

/**
 * Created by garu on 08/09/17.
 */

class ProductionLogger : Timber.Tree() {

    override fun log(priority: Int, tag: String, message: String, throwable: Throwable) {
        when (priority) {
            Log.INFO -> Log.i(tag, message)
            Log.WARN -> Log.w(tag, message)
            Log.ERROR -> handleError(tag, message, throwable)
        }
    }

    private fun handleError(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            Log.e(tag, message, throwable)
        } else {
            Log.e(tag, message)
        }
    }

}
