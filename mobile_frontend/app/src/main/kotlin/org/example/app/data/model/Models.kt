package org.example.app.data.model

/**
 * PUBLIC_INTERFACE
 * Kotlin data models aligned with the FastAPI backend OpenAPI schema.
 * Fields use snake_case to match JSON from backend and Moshi uses reflection to map directly.
 */

data class Coordinates(
    val lat: Double,
    val lon: Double
)

data class Location(
    val name: String,
    val country: String? = null,
    val state: String? = null,
    val coordinates: Coordinates
)

data class CurrentWeather(
    val temperature_c: Double,
    val wind_speed_kph: Double? = null,
    val wind_direction_deg: Double? = null,
    val weather_code: Int? = null,
    val condition_text: String? = null,
    val condition_icon: String? = null,
    val observation_time_iso: String? = null
)

data class CurrentWeatherResponse(
    val location: Location,
    val current: CurrentWeather
)

data class DailyForecastItem(
    val date: String,
    val min_temp_c: Double? = null,
    val max_temp_c: Double? = null,
    val weather_code: Int? = null,
    val condition_text: String? = null,
    val condition_icon: String? = null
)

data class HourlyForecastItem(
    val time_iso: String,
    val temp_c: Double? = null,
    val weather_code: Int? = null,
    val condition_text: String? = null,
    val condition_icon: String? = null
)

data class ForecastResponse(
    val location: Location,
    val daily: List<DailyForecastItem>? = emptyList(),
    val hourly: List<HourlyForecastItem>? = emptyList()
)

data class LocationSearchItem(
    val id: String? = null,
    val name: String,
    val country: String? = null,
    val state: String? = null,
    val coordinates: Coordinates
)

data class LocationSearchResponse(
    val results: List<LocationSearchItem> = emptyList()
)

data class ReverseGeocodeResponse(
    val result: LocationSearchItem? = null
)
