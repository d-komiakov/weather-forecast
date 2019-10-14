package tpformy.bunghole.weatherforecast.di.component

import dagger.Component
import tpformy.bunghole.weatherforecast.ForecastApp
import tpformy.bunghole.weatherforecast.di.module.ForecastModule
import tpformy.bunghole.weatherforecast.di.module.ApplicationModule
import tpformy.bunghole.weatherforecast.forecast.view.ForecastActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ForecastModule::class])
interface ApplicationComponent {
    fun inject(application: ForecastApp)
    fun inject(activity: ForecastActivity)
}