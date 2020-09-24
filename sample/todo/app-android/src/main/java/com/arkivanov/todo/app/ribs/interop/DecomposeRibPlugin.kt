package com.arkivanov.todo.app.ribs.interop

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.backpressed.BackPressedDispatcher
import com.arkivanov.decompose.lifecycle.LifecycleRegistry
import com.arkivanov.decompose.lifecycle.asDecomposeLifecycle
import com.arkivanov.decompose.lifecycle.setLifecycle
import com.arkivanov.decompose.statekeeper.ParcelableContainer
import com.arkivanov.decompose.statekeeper.StateKeeperDispatcher
import com.badoo.ribs.core.plugin.BackPressHandler
import com.badoo.ribs.core.plugin.NodeLifecycleAware
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.plugin.SavesInstanceState
import com.badoo.ribs.core.plugin.ViewAware

internal class DecomposeRibPlugin<T : Any>(
    private val decomposeComponentBuilder: DecomposeComponentBuilder<T>,
    savedInstanceState: Bundle?
) : Plugin, NodeLifecycleAware, ViewAware<RibViewContainer>, BackPressHandler, SavesInstanceState {

    private val backPressedDispatcher = BackPressedDispatcher()
    private val lifecycleRegistry = LifecycleRegistry()
    private val stateKeeperDispatcher = StateKeeperDispatcher(savedInstanceState?.getDecomposeState())

    private val component =
        decomposeComponentBuilder.createComponent(
            DefaultComponentContext(
                lifecycle = lifecycleRegistry,
                stateKeeper = stateKeeperDispatcher,
                backPressedDispatcher = backPressedDispatcher
            )
        )

    override fun onAttach(nodeLifecycle: Lifecycle) {
        nodeLifecycle.addObserver(AndroidLifecycleObserver(lifecycleRegistry))
    }

    override fun onViewCreated(view: RibViewContainer, viewLifecycle: Lifecycle) {
        ViewTreeLifecycleOwner.set(view.androidView) { viewLifecycle }
        view.androidView.setLifecycle(viewLifecycle.asDecomposeLifecycle())

        decomposeComponentBuilder.attachView(view.androidView, component)
    }

    override fun handleBackPress(): Boolean = backPressedDispatcher.onBackPressed()

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDecomposeState(stateKeeperDispatcher.save())
    }

    private companion object {
        private const val KEY_DECOMPOSE_STATE = "DECOMPOSE_STATE"

        private fun Bundle.getDecomposeState(): ParcelableContainer? {
            classLoader = ParcelableContainer::class.java.classLoader

            return getParcelable(KEY_DECOMPOSE_STATE)
        }

        private fun Bundle.putDecomposeState(state: ParcelableContainer) {
            putParcelable(KEY_DECOMPOSE_STATE, state)
        }
    }
}
