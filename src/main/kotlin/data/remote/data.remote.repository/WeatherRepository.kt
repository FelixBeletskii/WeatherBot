package org.example.data.remote.data.remote.repository

import data.remote.data.remote.models.CurrentWeather
import data.remote.data.remote.models.ReversedCountry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.data.remote.data.remote.api.ReversedGeocodingApi
import org.example.data.remote.data.remote.api.WeatherApi

class WeatherRepository(private val weatherApi: WeatherApi, private val reversedGeocodingApi: ReversedGeocodingApi) {
        suspend fun getCurrentWeather(apiKey:String,countryName:String,airQualityData:String):CurrentWeather{
           return withContext(Dispatchers.IO){
                weatherApi.getCurrentWeather(apiKey, countryName, airQualityData)
            }.await()
        }
    suspend fun getReversGeocodingCountryName(latitude:String,longitude:String, format: String): ReversedCountry{
        return withContext(Dispatchers.IO){
            reversedGeocodingApi.getCountryByCoordinates(latitude,longitude,format)
        }.await()
    }
}