package tpformy.bunghole.weatherforecast.forecast

interface ForecastStore {
    fun getForecast(): ForecastDto
}

interface ForecastView {
    fun showForecast(temperature: Int, windSpeed: Int, clouds: Int, isUpToDate: Boolean)
    fun showProgress()
    fun hideProgress()
    fun showError()
}

interface ForecastPresenter {
    fun bindView(view: ForecastView)
    fun releaseView()
    fun updateForecast()
}