package org.example.app.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.example.app.R

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.cardTitle).text = getString(R.string.nav_favorites)
        view.findViewById<TextView>(R.id.primaryText).text = "No favorites yet"
        view.findViewById<TextView>(R.id.secondaryText).text = "Search a location and add to favorites"
    }
}
