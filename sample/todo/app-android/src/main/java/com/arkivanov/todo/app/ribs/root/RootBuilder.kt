package com.arkivanov.todo.app.ribs.root

import com.arkivanov.todo.app.ribs.root.Root.Customisation
import com.arkivanov.todo.app.ribs.root.Root.Dependency
import com.arkivanov.todo.app.ribs.root.RootRooter.Configuration
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature

class RootBuilder(
    private val dependency: Dependency
) : SimpleBuilder<Root>() {

    override fun build(buildParams: BuildParams<Nothing?>): Root {
        val customisation = buildParams.getOrDefault(Customisation())

        val backStackFeature =
            BackStackFeature<Configuration>(
                buildParams = buildParams,
                initialConfiguration = Configuration.Todo
            )

        val router =
            RootRooter(
                buildParams = buildParams,
                routingSource = backStackFeature,
                todoRootBuilder = TodoRootComponentBuilder(dependency.context),
                counterRootBuilder = CounterRootComponentBuilder()
            )

        return RootNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(router, RootInteractor(buildParams, backStackFeature))
        )
    }
}
