package com.example.whenweather.ui.snow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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
        val textView: TextView = root.findViewById(R.id.Snow)

        // TODO: Remove
        val snowChanceTextView: TextView = root.findViewById(R.id.chance_of_snow_text_view)

        // val button: Button = root.findViewById(R.id.expand_less_icon)

        snowViewModel.text.observe(this, Observer {
            textView.text = it

            //snowChanceTextView.text = "%50 chance of snow"
            // TODO: Get information from MainActivity
            expand_collapse_icon.setOnClickListener(View.OnClickListener {
                val animation = AnimationUtils.loadAnimation(context, R.anim.slide_up)
                expand_collapse_icon.startAnimation(animation)
            })
        })
        return root
    }
}