package com.arkivanov.todo.app.ribs.root

import android.view.ViewGroup
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.sample.counter.shared.counterRootView
import com.arkivanov.sample.counter.shared.root.CounterRootContainer
import com.arkivanov.todo.app.ribs.interop.DecomposeComponentBuilder

internal class CounterRootComponentBuilder : DecomposeComponentBuilder<CounterRootContainer> {

    override fun createComponent(componentContext: ComponentContext): CounterRootContainer =
        CounterRootContainer(componentContext)

    override fun attachView(parent: ViewGroup, component: CounterRootContainer) {
        parent.counterRootView(component.model)
    }
}
