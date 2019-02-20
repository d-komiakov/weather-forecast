package tpformy.bunghole.weatherforecast.utils.task

import android.os.Handler
import java.util.concurrent.Executor

interface TaskManager {
    fun <TResult> runTask(backgroundAction: () -> TResult,
                          uiCallback: (TResult) -> Unit,
                          errorUiCallback: (Throwable) -> Unit)
}

class AndroidTaskManager(private val executor: Executor, private val mainHandler: Handler) : TaskManager {
    override fun <TResult> runTask(backgroundAction: () -> TResult,
                                   uiCallback: (TResult) -> Unit,
                                   errorUiCallback: (Throwable) -> Unit) {
        executor.execute {
            try {
                val result = backgroundAction()
                mainHandler.post {
                    uiCallback(result)
                }
            } catch (t: Throwable) {
                mainHandler.post {
                    errorUiCallback(t)
                }
            }
        }
    }

}