package org.example.app.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.app.data.model.CurrentWeatherResponse
import org.example.app.data.model.ForecastResponse
import org.example.app.data.model.LocationSearchResponse
import org.example.app.data.model.ReverseGeocodeResponse
import org.example.app.data.remote.NetworkModule

/**
 * PUBLIC_INTERFACE
 * WeatherRepository exposes suspend functions to retrieve weather and location data.
 */
class WeatherRepository {

    suspend fun currentWeather(
        q: String? = null,
        lat: Double? = null,
        lon: Double? = null
    ): Result<CurrentWeatherResponse> = safeCall {
        NetworkModule.api.getCurrentWeather(q, lat, lon)
    }

    suspend fun forecast(
        q: String? = null,
        lat: Double? = null,
        lon: Double? = null,
        days: Int? = 3
    ): Result<ForecastResponse> = safeCall {
        NetworkModule.api.getForecast(q, lat, lon, days)
    }

    suspend fun search(query: String): Result<LocationSearchResponse> = safeCall {
        NetworkModule.api.searchLocations(query)
    }

    suspend fun reverse(lat: Double, lon: Double): Result<ReverseGeocodeResponse> = safeCall {
        NetworkModule.api.reverseGeocode(lat, lon)
    }

    private suspend fun <T> safeCall(block: suspend () -> T): Result<T> =
        withContext(Dispatchers.IO) {
            try {
                Result.success(block())
            } catch (t: Throwable) {
                Result.failure(t)
            }
        }
}
