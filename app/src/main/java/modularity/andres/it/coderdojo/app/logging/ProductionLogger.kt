package modularity.andres.it.coderdojo.app.logging

import android.util.Log

import timber.log.Timber

/**
 * Created by garu on 08/09/17.
 */

class ProductionLogger : Timber.Tree() {

    companion object {
        private const val TAG = "CoderdojoEvents"
    }

    override fun log(priority: Int, tag: String, message: String, throwable: Throwable) {
        when (priority) {
            Log.INFO -> Log.i(TAG, message)
            Log.WARN -> Log.w(TAG, message)
            Log.ERROR -> handleError(TAG, message, throwable)
        }
    }

    private fun handleError(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            Log.e(TAG, message, throwable)
        } else {
            Log.e(TAG, message)
        }
    }

}
