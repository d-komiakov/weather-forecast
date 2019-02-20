package tpformy.bunghole.weatherforecast.utils.task

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object TaskManagerFactory {
    lateinit var executor: Executor
    lateinit var mainHandler: Handler

    fun createTaskManager(): TaskManager {
        if (!TaskManagerFactory::executor.isInitialized) {
            executor = Executors.newFixedThreadPool(4)
        }

        if (!TaskManagerFactory::mainHandler.isInitialized) {
            mainHandler = Handler(Looper.getMainLooper())
        }

        return AndroidTaskManager(executor, mainHandler)
    }
}