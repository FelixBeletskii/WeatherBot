package org.example.data.remote.data.remote.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.example.data.remote.data.remote.api.ReversedGeocodingApi
import org.example.data.remote.data.remote.api.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val WEATHER_BASE_URL ="https://www.api.weatherapi.com/v1/"
private const val REVERSE_GEOCODE_BASE_URL = "https://nominatim.openstreetmap.org/"
private const val API_KEY = "0af6b4c677157cca8277afd89fa80f75"

enum class RetrofitType(val baseUrl: String){
    WEATHER(WEATHER_BASE_URL),
    REVERSE_GEOCODER(REVERSE_GEOCODE_BASE_URL)
}
class RetrofitClient {
    fun getClient(): OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
        return okHttpClient.build()
    }
    fun getRetrofit(retrofitType: RetrofitType): Retrofit{
        return Retrofit.Builder()
            .baseUrl(retrofitType.baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    fun getWeatherApi(retrofit: Retrofit):WeatherApi{
        return retrofit.create(WeatherApi::class.java)

    }
    fun getReversGeocodingApi(retrofit: Retrofit): ReversedGeocodingApi{
        return retrofit.create(ReversedGeocodingApi::class.java)
    }
}