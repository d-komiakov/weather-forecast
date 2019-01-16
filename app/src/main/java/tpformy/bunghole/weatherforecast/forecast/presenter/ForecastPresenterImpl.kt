package tpformy.bunghole.weatherforecast.forecast.presenter

import tpformy.bunghole.weatherforecast.forecast.ForecastPresenter
import tpformy.bunghole.weatherforecast.forecast.ForecastStore
import tpformy.bunghole.weatherforecast.forecast.ForecastView
import tpformy.bunghole.weatherforecast.utils.background.TaskManager

class ForecastPresenterImpl(private val forecastStore: ForecastStore, private val taskManager: TaskManager): ForecastPresenter {

    private var view: ForecastView? = null

    override fun bindView(view: ForecastView) {
        this.view = view
    }

    override fun releaseView() {
        view = null
    }

    override fun updateForecast() {
        view?.showProgress()
        taskManager.runTask(
            backgroundAction = { forecastStore.getForecast() },
            uiCallback = { dto ->
                run {
                    view?.showForecast(dto.temperature, dto.windSpeed, dto.clouds, dto.isUpToDate)
                    view?.hideProgress()
                }
            },
            errorUiCallback = {
                view?.showError()
                view?.hideProgress()
            }
        )
    }

}