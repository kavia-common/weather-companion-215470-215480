package org.example.app.ui.dashboard

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

class DashboardFragment : Fragment() {

    private val vm: DashboardViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.cardTitle)
        val primary = view.findViewById<TextView>(R.id.primaryText)
        val secondary = view.findViewById<TextView>(R.id.secondaryText)

        title.text = getString(R.string.nav_dashboard)
        viewLifecycleOwner.lifecycleScope.launch {
            vm.state.collect { state ->
                primary.text = state.title
                secondary.text = state.subtitle
            }
        }
        vm.load()
    }
}
