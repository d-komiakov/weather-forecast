package tpformy.bunghole.weatherforecast.utils.task

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

object TaskManagerFactory {
    lateinit var backgroundHandler: Handler
    lateinit var mainHandler: Handler

    fun createTaskManager(): TaskManager {
        if (!TaskManagerFactory::backgroundHandler.isInitialized) {
            val backgroundHandlerThread = HandlerThread("TaskManagerThread")
            backgroundHandlerThread.start()
            backgroundHandler = Handler(backgroundHandlerThread.looper)
        }

        if (!TaskManagerFactory::mainHandler.isInitialized) {
            mainHandler = Handler(Looper.getMainLooper())
        }
        return AndroidTaskManager(backgroundHandler, mainHandler)
    }
}