package tpformy.bunghole.weatherforecast.utils.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import tpformy.bunghole.weatherforecast.utils.net.dto.ForecastDto

interface OpenWeatherApi {
    @GET("data/2.5/weather?id={id}&appid={appId}")
    fun getForecast(@Path("id") id: Int, @Path("appId") appId: String): Call<ForecastDto>
}