package com.arkivanov.todo.app.ribs.root

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.plugin.Plugin

class RootNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> RootView?)?,
    plugins: List<Plugin> = emptyList()
) : Node<RootView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Root
