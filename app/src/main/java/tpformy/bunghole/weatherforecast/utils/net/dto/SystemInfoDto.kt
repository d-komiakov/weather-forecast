package tpformy.bunghole.weatherforecast.utils.net.dto

data class SystemInfoDto(
    val type: Int,
    val id: Int,
    val message: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
//"type":1,
//"id":8166,
//"message":0.2064,
//"country":"AU",
//"sunrise":1485720272,
//"sunset":148576655