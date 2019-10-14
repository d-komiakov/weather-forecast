package tpformy.bunghole.weatherforecast

import android.app.Application
import tpformy.bunghole.weatherforecast.di.component.ApplicationComponent
import tpformy.bunghole.weatherforecast.di.component.DaggerApplicationComponent
import tpformy.bunghole.weatherforecast.di.module.ApplicationModule
import tpformy.bunghole.weatherforecast.di.module.ForecastModule

class ForecastApp : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .forecastModule(ForecastModule())
            .build()
        component.inject(this)
    }

    fun getComponent() = component
}