package tpformy.bunghole.weatherforecast.utils.dto

data class WeatherDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)