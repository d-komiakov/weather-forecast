package tpformy.bunghole.weatherforecast.utils.background

import android.os.Handler

interface TaskManager {
    fun <TResult> runTask(backgroundAction: () -> TResult,
                          uiCallback: (TResult) -> Unit,
                          errorUiCallback: (Throwable) -> Unit)
}

class AndroidTaskManager(private val backgroundHandler: Handler, private val mainHandler: Handler) : TaskManager {
    override fun <TResult> runTask(backgroundAction: () -> TResult,
                                   uiCallback: (TResult) -> Unit,
                                   errorUiCallback: (Throwable) -> Unit) {
        backgroundHandler.run {
            try {
                val result = backgroundAction()
                mainHandler.run {
                    uiCallback(result)
                }
            } catch (t: Throwable) {
                mainHandler.run {
                    errorUiCallback(t)
                }
            }
        }
    }

}