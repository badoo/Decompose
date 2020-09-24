package com.arkivanov.todo.app.ribs.root

import android.os.Parcelable
import com.arkivanov.sample.counter.shared.root.CounterRootContainer
import com.arkivanov.todo.app.ribs.interop.DecomposeComponentBuilder
import com.arkivanov.todo.app.ribs.interop.rib
import com.arkivanov.todo.app.ribs.root.RootRooter.Configuration
import com.arkivanov.todo.root.TodoRoot
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.action.AttachRibRoutingAction.Companion.attach
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature
import com.badoo.ribs.core.routing.history.Routing
import com.badoo.ribs.core.routing.transition.handler.TransitionHandler
import kotlinx.android.parcel.Parcelize

class RootRooter internal constructor(
    buildParams: BuildParams<*>,
    routingSource: BackStackFeature<Configuration>,
    private val todoRootBuilder: DecomposeComponentBuilder<TodoRoot>,
    private val counterRootBuilder: DecomposeComponentBuilder<CounterRootContainer>,
    transitionHandler: TransitionHandler<Configuration>? = null
) : Router<Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {

    sealed class Configuration : Parcelable {
        @Parcelize
        object Todo : Configuration()

        @Parcelize
        object Settings : Configuration()
    }

    override fun resolve(routing: Routing<Configuration>): RoutingAction =
        when (routing.configuration) {
            is Configuration.Todo -> attach(todoRootBuilder::rib)
            is Configuration.Settings -> attach(counterRootBuilder::rib)
        }
}
