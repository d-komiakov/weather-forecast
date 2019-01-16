package tpformy.bunghole.weatherforecast.utils.net.dto

import com.google.gson.annotations.SerializedName

data class PrecipitationDto(
    @SerializedName("1h")
    val oneHourVolume: Int?,
    @SerializedName("3h")
    val threeHoursVolume: Int?
)
