package com.example.whenweather.ui.rain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.whenweather.R

class RainFragment : Fragment() {

    private lateinit var rainViewModel: RainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rainViewModel =
            ViewModelProviders.of(this).get(RainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        rainViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}