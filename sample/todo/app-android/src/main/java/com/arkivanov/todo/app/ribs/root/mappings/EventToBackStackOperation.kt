package com.arkivanov.todo.app.ribs.root.mappings

import com.arkivanov.todo.app.ribs.root.RootRooter.Configuration
import com.arkivanov.todo.app.ribs.root.RootView.Event
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature
import com.badoo.ribs.core.routing.configuration.feature.operation.BackStackOperation
import com.badoo.ribs.core.routing.configuration.feature.operation.Pop
import com.badoo.ribs.core.routing.configuration.feature.operation.Push
import com.badoo.ribs.core.routing.configuration.feature.operation.Replace

internal object EventToBackStackOperation : (Event) -> BackStackFeature.Operation<Configuration> {

    override fun invoke(event: Event): BackStackFeature.Operation<Configuration> =
        BackStackFeature.Operation(event.toBackStackOperation())

    private fun Event.toBackStackOperation(): BackStackOperation<Configuration> =
        when (this) {
            is Event.TodoClicked -> Pop()
            is Event.SettingsClicked -> Push(Configuration.Settings)
        }
}
