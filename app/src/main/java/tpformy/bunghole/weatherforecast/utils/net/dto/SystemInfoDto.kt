package tpformy.bunghole.weatherforecast.utils.net.dto

data class SystemInfoDto(
    val type: Int,
    val id: Int,
    val message: Double,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)