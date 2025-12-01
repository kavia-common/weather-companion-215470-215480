package org.example.app.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.example.app.R

class ForecastFragment : Fragment() {

    private val vm: ForecastViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.cardTitle)
        val primary = view.findViewById<TextView>(R.id.primaryText)
        val secondary = view.findViewById<TextView>(R.id.secondaryText)

        title.text = getString(R.string.nav_forecast)
        viewLifecycleOwner.lifecycleScope.launch {
            vm.state.collect {
                primary.text = if (it.isLoading) "Loading..." else it.headline
                secondary.text = it.error ?: "Items: ${it.count}"
            }
        }
        vm.load()
    }
}
