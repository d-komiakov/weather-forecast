package tpformy.bunghole.weatherforecast.utils.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory


object ViridianFactory {

    private lateinit var jsonClient: OkHttpClient

    fun createApi(): OpenWeatherApi {
        if (!ViridianFactory::jsonClient.isInitialized) {
            createJsonClient()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .client(jsonClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create<OpenWeatherApi>(OpenWeatherApi::class.java)
    }

    private fun createJsonClient() {
       jsonClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                chain.proceed(request
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .build())
            }
            .build()
    }
}
