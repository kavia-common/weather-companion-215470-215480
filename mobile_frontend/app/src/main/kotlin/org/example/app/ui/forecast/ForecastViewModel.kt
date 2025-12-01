package org.example.app.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.app.data.WeatherRepository

data class ForecastState(
    val headline: String = "Forecast",
    val count: Int = 0
)

class ForecastViewModel : ViewModel() {
    private val repo = WeatherRepository()
    private val _state = MutableStateFlow(ForecastState())
    val state = _state.asStateFlow()

    fun load() {
        viewModelScope.launch {
            // placeholder: later call repo.forecast(...)
            _state.emit(ForecastState(headline = "Forecast ready", count = 0))
        }
    }
}
