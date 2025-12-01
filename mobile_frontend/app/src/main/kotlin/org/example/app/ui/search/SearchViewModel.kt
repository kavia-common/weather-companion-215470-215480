package org.example.app.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.app.data.WeatherRepository

data class SearchState(
    val query: String = "",
    val status: String = "Type to search",
    val resultsSummary: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * PUBLIC_INTERFACE
 * SearchViewModel debounced text search against backend location API.
 */
class SearchViewModel : ViewModel() {
    private val repo = WeatherRepository()
    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    // PUBLIC_INTERFACE
    fun updateQuery(q: String) {
        _state.value = _state.value.copy(query = q)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (q.isBlank()) {
                _state.emit(_state.value.copy(status = "Type to search", resultsSummary = "", isLoading = false, error = null))
                return@launch
            }
            _state.emit(_state.value.copy(status = "Searching...", resultsSummary = "", isLoading = true, error = null))
            delay(300) // simple debounce
            val result = repo.search(q)
            result.onSuccess { resp ->
                _state.emit(_state.value.copy(status = "Results", resultsSummary = "${resp.results.size} locations", isLoading = false, error = null))
            }.onFailure {
                _state.emit(_state.value.copy(status = "Error", resultsSummary = it.message ?: "Unknown error", isLoading = false, error = it.message))
            }
        }
    }
}
