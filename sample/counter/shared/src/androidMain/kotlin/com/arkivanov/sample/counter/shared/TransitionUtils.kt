package com.arkivanov.sample.counter.shared

import android.transition.Transition

internal inline fun <T : Transition> T.doOnEnd(crossinline block: () -> Unit): T {
    addListener(
        object : DefaultTransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                block()
            }
        }
    )

    return this
}
