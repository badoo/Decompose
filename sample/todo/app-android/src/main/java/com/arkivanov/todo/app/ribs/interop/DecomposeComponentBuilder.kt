package com.arkivanov.todo.app.ribs.interop

import android.view.ViewGroup
import com.arkivanov.decompose.ComponentContext
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.builder.BuildContext

interface DecomposeComponentBuilder<T : Any> {

    fun createComponent(componentContext: ComponentContext): T

    fun attachView(parent: ViewGroup, component: T)
}

fun DecomposeComponentBuilder<*>.rib(buildContext: BuildContext): Rib =
    DecomposeRibBuilder(this).build(buildContext, null)
