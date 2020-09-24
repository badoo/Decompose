package com.arkivanov.todo.app.ribs.root

import androidx.lifecycle.Lifecycle
import com.arkivanov.todo.app.ribs.root.RootRooter.Configuration
import com.arkivanov.todo.app.ribs.root.mappings.EventToBackStackOperation
import com.badoo.reaktive.disposable.scope.disposableScope
import com.badoo.reaktive.observable.mapNotNull
import com.badoo.ribs.android.subscribe
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature

internal class RootInteractor(
    buildParams: BuildParams<*>,
    private val backStackFeature: BackStackFeature<Configuration>
) : Interactor<Root, RootView>(
    buildParams = buildParams
) {

    override fun onViewCreated(view: RootView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)

        val scope =
            disposableScope {
                view.mapNotNull(EventToBackStackOperation).subscribeScoped(onNext = backStackFeature::accept)
            }

        viewLifecycle.subscribe(onDestroy = scope::dispose)
    }
}
