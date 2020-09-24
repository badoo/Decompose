package com.arkivanov.decompose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.arkivanov.decompose.lifecycle.requireLifecycle
import com.arkivanov.decompose.lifecycle.subscribe
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver

fun <V : View, T : Any> V.render(value: Value<T>, renderer: V.(T) -> Unit) {
    val observer: ValueObserver<T> = { this@render.renderer(it) }

    requireLifecycle().subscribe(
        onStart = {
            this@render.renderer(value.value)
            value.subscribe(observer)
        },
        onStop = { value.unsubscribe(observer) }
    )
}

fun ViewGroup.addView(@LayoutRes layoutId: Int): View {
    val view = LayoutInflater.from(context).inflate(layoutId, this, false)
    addView(view)

    return view
}
