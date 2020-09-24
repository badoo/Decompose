package com.arkivanov.decompose

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.arkivanov.decompose.lifecycle.LifecycleRegistry
import com.arkivanov.decompose.lifecycle.MergedLifecycle
import com.arkivanov.decompose.lifecycle.destroy
import com.arkivanov.decompose.lifecycle.doOnDestroy
import com.arkivanov.decompose.lifecycle.requireLifecycle
import com.arkivanov.decompose.lifecycle.resume
import com.arkivanov.decompose.lifecycle.setLifecycle
import com.arkivanov.decompose.value.Value

fun <T : Any> View.renderChild(
    models: Value<T>,
    @IdRes containerId: Int,
    addView: (ViewGroup, T) -> View
) {
    ChildViewHolder(this, containerId, addView)
        .render(models)
}

fun <T : Any> View.renderChild(
    models: Value<T>,
    @IdRes containerId: Int,
    switchViews: (container: ViewGroup, oldChild: View?, T) -> View
) {
    ChildViewHolder(this, containerId, switchViews)
        .render(models)
}

private class ChildViewHolder<in T : Any>(
    private val parent: View,
    @IdRes private val containerId: Int,
    private val switchViews: (container: ViewGroup, oldChild: View?, T) -> View,
) {

    constructor(parent: View, @IdRes containerId: Int, addView: (ViewGroup, T) -> View) : this(
        parent = parent,
        containerId = containerId,
        switchViews = { container, oldChild, model ->
            oldChild?.also(container::removeView)
            addView(container, model)
        }
    )

    private val container: ViewGroup = parent.findViewById(containerId)
    private val lifecycle = parent.requireLifecycle()
    private var holder: Holder<T>? = null

    init {
        lifecycle.doOnDestroy(::destroyHolder)
    }

    fun render(model: T) {
        if (holder?.model != model) {
            holder?.lifecycleRegistry?.destroy()

            val lifecycleRegistry = LifecycleRegistry()
            container.setLifecycle(MergedLifecycle(lifecycle, lifecycleRegistry))

            val view = switchViews(container, holder?.view, model)
            lifecycleRegistry.resume()
            holder = Holder(view, model, lifecycleRegistry)
        }
    }

    fun render(models: Value<T>) {
        parent.render(models) {
            render(it)
        }
    }

    private fun destroyHolder() {
        val holder = holder ?: return
        this.holder = null
        holder.lifecycleRegistry.destroy()
        container.setLifecycle(null)
    }

    private class Holder<out T : Any>(
        val view: View,
        val model: T,
        val lifecycleRegistry: LifecycleRegistry
    )
}
