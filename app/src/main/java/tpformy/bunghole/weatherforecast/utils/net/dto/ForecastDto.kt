package tpformy.bunghole.weatherforecast.utils.net.dto

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("coord")
    val coordinates: CoordinatesDto?,
    val weather: List<WeatherDto>,
    val base: String,
    val main: MainWeatherDto,
    val visibility: Int,
    val wind: WindDto,
    val clouds: CloudsDto,
    val rain: PrecipitationDto?,
    val snow: PrecipitationDto?,
    @SerializedName("dt")
    val forecastTime: Long,
    @SerializedName("sys")
    val systemInfo: SystemInfoDto,
    val id: Int,
    val name: String,
    val cod: Int
)
