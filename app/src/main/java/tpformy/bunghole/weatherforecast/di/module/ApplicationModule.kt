package tpformy.bunghole.weatherforecast.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import tpformy.bunghole.weatherforecast.ForecastApp
import javax.inject.Singleton

@Module
class ApplicationModule(private val forecastApp: ForecastApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return  forecastApp
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return forecastApp.applicationContext
    }
}