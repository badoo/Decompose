package com.arkivanov.todo.app.ribs.interop

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams

internal class DecomposeNode<T : Any> internal constructor(
    buildParams: BuildParams<Nothing?>,
    decomposeComponentBuilder: DecomposeComponentBuilder<T>
) : Node<RibViewContainer>(
    buildParams = buildParams,
    viewFactory = { RibViewContainer(it.context) },
    plugins = listOf(DecomposeRibPlugin(decomposeComponentBuilder, buildParams.savedInstanceState))
)
