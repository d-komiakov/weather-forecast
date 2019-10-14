package tpformy.bunghole.weatherforecast.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import tpformy.bunghole.weatherforecast.R
import tpformy.bunghole.weatherforecast.forecast.ForecastPresenter
import tpformy.bunghole.weatherforecast.forecast.ForecastStore
import tpformy.bunghole.weatherforecast.forecast.model.ForecastStoreImpl
import tpformy.bunghole.weatherforecast.forecast.presenter.ForecastPresenterImpl
import tpformy.bunghole.weatherforecast.utils.net.ApiFactory
import tpformy.bunghole.weatherforecast.utils.net.OpenWeatherApi
import tpformy.bunghole.weatherforecast.utils.persistence.AndroidPersistence
import tpformy.bunghole.weatherforecast.utils.persistence.Persistence
import tpformy.bunghole.weatherforecast.utils.task.TaskManager
import tpformy.bunghole.weatherforecast.utils.task.TaskManagerFactory
import javax.inject.Singleton

@Module
class ForecastModule {

    @Provides
    @Singleton
    fun persistence(context: Context): Persistence {
        return AndroidPersistence(context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE))
    }

    @Provides
    @Singleton
    fun api(): OpenWeatherApi {
        return ApiFactory.createApi()
    }

    @Provides
    @Singleton
    fun forecastStore(api: OpenWeatherApi, persistence: Persistence): ForecastStore {
        return ForecastStoreImpl(api,persistence)
    }

    @Provides
    @Singleton
    fun taskManager(): TaskManager {
        return TaskManagerFactory.createTaskManager()
    }

    @Provides
    @Singleton
    fun forecastPresenter(forecastStore: ForecastStore, taskManager: TaskManager): ForecastPresenter {
        return ForecastPresenterImpl(forecastStore, taskManager)
    }
}