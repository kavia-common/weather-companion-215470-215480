package org.example.app.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.example.app.R

class MapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.cardTitle).text = getString(R.string.nav_map)
        view.findViewById<TextView>(R.id.primaryText).text = "Map placeholder"
        view.findViewById<TextView>(R.id.secondaryText).text = "Location features coming soon"
    }
}
