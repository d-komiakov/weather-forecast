package tpformy.bunghole.weatherforecast.utils.dto

import com.google.gson.annotations.SerializedName

data class MainWeatherDto(
    val temp: Float,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("temp_max")
    val tempMax: Float
)