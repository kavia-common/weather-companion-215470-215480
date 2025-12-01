package org.example.app.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.app.data.WeatherRepository

data class DashboardState(
    val title: String = "Current Weather",
    val subtitle: String = "Tap Search to choose a city"
)

class DashboardViewModel : ViewModel() {
    private val repo = WeatherRepository()

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun load() {
        viewModelScope.launch {
            // Placeholder: we could fetch by default coordinates or recent favorite
            _state.emit(_state.value.copy(subtitle = "Ready to fetch from backend at runtime"))
        }
    }
}
