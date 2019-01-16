package tpformy.bunghole.weatherforecast.forecast

data class ForecastDto(
    val temperature: Int,
    val windSpeed: Int,
    val clouds: Int,
    val isUpToDate: Boolean
)