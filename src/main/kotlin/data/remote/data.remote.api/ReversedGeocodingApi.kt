package org.example.data.remote.data.remote.api

import data.remote.data.remote.models.ReversedCountry
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ReversedGeocodingApi {
    @GET("reverse")
    fun getCountryByCoordinates(
        @Query("lat") latitude :String,
        @Query("lon") longitude: String,
        @Query("format") formatData: String
    ): Deferred<ReversedCountry>

}