package com.arkivanov.sample.counter.shared

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arkivanov.decompose.addView
import com.arkivanov.decompose.render
import com.arkivanov.sample.counter.shared.counter.Counter

@Suppress("FunctionName")
fun ViewGroup.counterView(model: Counter.Model): View {
    val view = addView(R.layout.counter)

    val textView = view.findViewById<TextView>(R.id.text)

    textView.render(model.data) { data ->
        text = data.text
    }

    return view
}
