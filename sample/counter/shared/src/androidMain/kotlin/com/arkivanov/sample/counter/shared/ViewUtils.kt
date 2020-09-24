package com.arkivanov.sample.counter.shared

import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.arkivanov.decompose.renderChild
import com.arkivanov.decompose.value.Value

internal fun <T : Any> View.renderChild(
    models: Value<T>,
    @IdRes containerId: Int,
    delayedTransition: () -> Transition,
    addView: (ViewGroup, T) -> View
) {
    this@renderChild.renderChild(models, containerId) { container, oldChild, model ->
        TransitionManager.beginDelayedTransition(
            container,
            delayedTransition().doOnEnd {
                oldChild?.also(container::removeView)
            }
        )

        oldChild?.visibility = View.GONE

        addView(container, model)
    }
}
