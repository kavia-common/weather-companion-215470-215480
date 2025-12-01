package org.example.app.data.remote

import org.example.app.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * PUBLIC_INTERFACE
 * ApiService defines REST endpoints to the Weather Companion backend.
 * Base URL is provided via BuildConfig.BASE_URL.
 * Endpoints correspond to FastAPI routes:
 * - GET /weather/current
 * - GET /weather/forecast
 * - GET /location/search
 * - GET /location/reverse
 */
interface ApiService {

    // PUBLIC_INTERFACE
    @GET("weather/current")
    suspend fun getCurrentWeather(
        @Query("q") q: String? = null,
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null
    ): CurrentWeatherResponse

    // PUBLIC_INTERFACE
    @GET("weather/forecast")
    suspend fun getForecast(
        @Query("q") q: String? = null,
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null,
        @Query("days") days: Int? = 3
    ): ForecastResponse

    // PUBLIC_INTERFACE
    @GET("location/search")
    suspend fun searchLocations(
        @Query("q") query: String
    ): LocationSearchResponse

    // PUBLIC_INTERFACE
    @GET("location/reverse")
    suspend fun reverseGeocode(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): ReverseGeocodeResponse
}
