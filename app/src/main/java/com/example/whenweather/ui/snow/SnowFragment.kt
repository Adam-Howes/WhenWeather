package com.example.whenweather.ui.snow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.whenweather.R
import kotlinx.android.synthetic.main.fragment_snow.*

class SnowFragment : Fragment() {

    private lateinit var snowViewModel: SnowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        snowViewModel =
            ViewModelProviders.of(this).get(SnowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_snow, container, false)

        // TODO: Remove
        val snowChanceTextView: TextView = root.findViewById(R.id.chance_of_snow_text_view)

        var expandCollapseToggle = 0

        snowViewModel.text.observe(this, Observer {

            snowChanceTextView.text = "(WIP) chance of snow"
            // TODO: Get information from MainActivity
            expand_collapse_icon.setOnClickListener(View.OnClickListener {

                if (expandCollapseToggle == 0) {
                    expand_collapse_icon.animate().translationY(500f).start()
                    expand_collapse_icon.animate().rotation(180f).start()
                    humidity_snow_text_view.visibility = View.VISIBLE
                    pressure_snow_text_view.visibility = View.VISIBLE
                    wind_snow_text_view.visibility = View.VISIBLE
                    temperature_snow_text_view.visibility = View.VISIBLE
                    expandCollapseToggle = 1
                } else {
                    expand_collapse_icon.animate().translationY(0f).start()
                    expand_collapse_icon.animate().rotation(0f).start()
                    humidity_snow_text_view.visibility = View.GONE
                    pressure_snow_text_view.visibility = View.GONE
                    wind_snow_text_view.visibility = View.GONE
                    temperature_snow_text_view.visibility = View.GONE
                    expandCollapseToggle = 0
                }
            })
        })
        return root
    }
}