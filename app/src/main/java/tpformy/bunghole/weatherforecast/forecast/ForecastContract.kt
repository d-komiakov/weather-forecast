package tpformy.bunghole.weatherforecast.forecast

interface ForecastStore {
    fun getForecast(): ForecastDto
}

interface ForecastView {
    fun showForecast(temperature: Int, windSpeed: Int, clouds: Int)
    fun showProgress()
    fun hideProgress()
    fun error()
}

interface ForecastPresenter {
    fun start()
    fun stop()
    fun bindView(view: ForecastView)
    fun releaseView()
    fun updateForecast()
}