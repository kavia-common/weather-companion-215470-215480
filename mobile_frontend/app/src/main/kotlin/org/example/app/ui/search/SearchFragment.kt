package org.example.app.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.example.app.R

class SearchFragment : Fragment() {

    private val vm: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.cardTitle)
        val primary = view.findViewById<TextView>(R.id.primaryText)
        val secondary = view.findViewById<TextView>(R.id.secondaryText)
        val input = view.findViewById<EditText>(R.id.searchInput)

        title.text = getString(R.string.nav_search)

        input.addTextChangedListener {
            vm.updateQuery(it?.toString().orEmpty())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.state.collect { s ->
                primary.text = if (s.isLoading) "Searching..." else s.status
                secondary.text = s.error ?: s.resultsSummary
            }
        }
    }
}
