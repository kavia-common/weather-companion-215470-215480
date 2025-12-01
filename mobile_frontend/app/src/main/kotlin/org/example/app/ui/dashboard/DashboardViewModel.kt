package org.example.app.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.app.data.WeatherRepository

data class DashboardState(
    val title: String = "Current Weather",
    val subtitle: String = "Tap Search to choose a city",
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * PUBLIC_INTERFACE
 * DashboardViewModel loads current weather on demand.
 * For demo, it fetches Berlin by default. Adjust to user-selected city as needed.
 */
class DashboardViewModel : ViewModel() {
    private val repo = WeatherRepository()

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    // PUBLIC_INTERFACE
    fun load(defaultQuery: String = "Berlin") {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isLoading = true, error = null, subtitle = "Loading..."))
            val result = repo.currentWeather(q = defaultQuery)
            result.onSuccess { resp ->
                val temp = resp.current.temperature_c
                val loc = resp.location.name
                _state.emit(
                    _state.value.copy(
                        isLoading = false,
                        error = null,
                        title = "Current Weather",
                        subtitle = "$loc • ${"%.1f".format(temp)}°C"
                    )
                )
            }.onFailure { t ->
                _state.emit(
                    _state.value.copy(
                        isLoading = false,
                        error = t.message ?: "Unknown error",
                        subtitle = "Failed to load: ${t.message ?: "Unknown"}"
                    )
                )
            }
        }
    }
}
