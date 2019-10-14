package tpformy.bunghole.weatherforecast.utils.net

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tpformy.bunghole.weatherforecast.utils.net.dto.ForecastDto

interface OpenWeatherApi {
    @GET("data/2.5/weather")
    fun getForecast(@Query("id") id: Int, @Query("appid") appId: String): Call<ForecastDto>
    @GET("data/2.5/weather")
    fun getForecastObservable(@Query("id") id: Int, @Query("appid") appId: String): Single<ForecastDto>
}