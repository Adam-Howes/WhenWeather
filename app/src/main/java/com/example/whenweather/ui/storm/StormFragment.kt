package com.example.whenweather.ui.storm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.whenweather.R

class StormFragment : Fragment() {

    private lateinit var stormViewModel: StormViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stormViewModel =
            ViewModelProviders.of(this).get(StormViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        stormViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}