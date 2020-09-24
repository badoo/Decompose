package com.arkivanov.todo.app.ribs.interop

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.Builder

internal class DecomposeRibBuilder<T : Any>(
    private val decomposeComponentBuilder: DecomposeComponentBuilder<T>
) : Builder<Nothing?, DecomposeNode<T>>() {

    override fun build(buildParams: BuildParams<Nothing?>): DecomposeNode<T> =
        DecomposeNode(buildParams, decomposeComponentBuilder)
}
