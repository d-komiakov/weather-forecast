package tpformy.bunghole.weatherforecast.forecast.model

import tpformy.bunghole.weatherforecast.forecast.ForecastDto
import tpformy.bunghole.weatherforecast.forecast.ForecastStore
import tpformy.bunghole.weatherforecast.utils.net.OpenWeatherApi
import tpformy.bunghole.weatherforecast.utils.persistence.Persistence

class ForecastStoreImpl(private val api: OpenWeatherApi, private val persistence: Persistence) : ForecastStore {

    companion object {
        private const val TEMPERATURE = "tpformy.bunghole.weatherforecast_temperature"
        private const val WIND_SPEED = "tpformy.bunghole.weatherforecast_wind_speed"
        private const val CLOUDS = "tpformy.bunghole.weatherforecast_clouds"
    }

    override fun getForecast(): ForecastDto {
        val call = api.getForecast(498817, "2ee8774876c2de82d97b985b0f67486b")
        val response = call.execute()

        if (response.code() == 200) {
            val body = response.body()!!

            val temperature = temperatureInCelsiumDegrees(body.main.temp)
            val windSpeed = body.wind.speed
            val clouds = body.clouds.all

            persistence.putIntValues(hashMapOf(TEMPERATURE to temperature, WIND_SPEED to windSpeed, CLOUDS to clouds))

            return ForecastDto(
                temperature,
                windSpeed,
                clouds,
                true
            )

        } else {
            return ForecastDto(
                persistence.getIntValue(TEMPERATURE),
                persistence.getIntValue(WIND_SPEED),
                persistence.getIntValue(CLOUDS),
                false
            )
        }
    }

    private fun temperatureInCelsiumDegrees(temp: Float) =
        (temp - 273).toInt()

}