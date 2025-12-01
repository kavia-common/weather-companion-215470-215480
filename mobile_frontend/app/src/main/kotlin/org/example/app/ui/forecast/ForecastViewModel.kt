package org.example.app.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.app.data.WeatherRepository

data class ForecastState(
    val headline: String = "Forecast",
    val count: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * PUBLIC_INTERFACE
 * ForecastViewModel fetches forecast for a default city (Berlin) for demo.
 */
class ForecastViewModel : ViewModel() {
    private val repo = WeatherRepository()
    private val _state = MutableStateFlow(ForecastState())
    val state = _state.asStateFlow()

    // PUBLIC_INTERFACE
    fun load(defaultQuery: String = "Berlin", days: Int = 3) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isLoading = true, error = null, headline = "Loading forecast..."))
            val result = repo.forecast(q = defaultQuery, days = days)
            result.onSuccess { resp ->
                val dailyCount = resp.daily?.size ?: 0
                val loc = resp.location.name
                _state.emit(
                    _state.value.copy(
                        isLoading = false,
                        error = null,
                        headline = "$loc • $days-day forecast",
                        count = dailyCount
                    )
                )
            }.onFailure { t ->
                _state.emit(
                    _state.value.copy(
                        isLoading = false,
                        error = t.message ?: "Unknown error",
                        headline = "Failed to load forecast"
                    )
                )
            }
        }
    }
}
