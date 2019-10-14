package tpformy.bunghole.weatherforecast.utils.task

interface TaskManager {
    fun <TResult> runTask(backgroundAction: () -> TResult,
                          uiCallback: (TResult) -> Unit,
                          errorUiCallback: (Throwable) -> Unit)
}

