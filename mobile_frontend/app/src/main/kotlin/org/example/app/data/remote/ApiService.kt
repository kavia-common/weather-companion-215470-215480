package org.example.app.data.remote

import org.example.app.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * PUBLIC_INTERFACE
 * ApiService defines REST endpoints to the Weather Companion backend.
 * Base URL is provided via BuildConfig.BASE_URL.
 */
interface ApiService {

    @GET("weather/current")
    suspend fun getCurrentWeather(
        @Query("q") q: String? = null,
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null
    ): CurrentWeatherResponse

    @GET("weather/forecast")
    suspend fun getForecast(
        @Query("q") q: String? = null,
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null,
        @Query("days") days: Int? = 3
    ): ForecastResponse

    @GET("location/search")
    suspend fun searchLocations(
        @Query("q") query: String
    ): LocationSearchResponse

    @GET("location/reverse")
    suspend fun reverseGeocode(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): ReverseGeocodeResponse
}
