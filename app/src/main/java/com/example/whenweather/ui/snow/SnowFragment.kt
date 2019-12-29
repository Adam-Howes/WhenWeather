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
        val textView: TextView = root.findViewById(R.id.Snow)
        snowViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}