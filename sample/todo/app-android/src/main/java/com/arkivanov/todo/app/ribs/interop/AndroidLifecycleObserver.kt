package com.arkivanov.todo.app.ribs.interop

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.arkivanov.decompose.lifecycle.Lifecycle as DecomposeLifecycle

internal class AndroidLifecycleObserver(
    private val delegate: DecomposeLifecycle.Callbacks
) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        delegate.onCreate()
    }

    override fun onStart(owner: LifecycleOwner) {
        delegate.onStart()
    }

    override fun onResume(owner: LifecycleOwner) {
        delegate.onResume()
    }

    override fun onPause(owner: LifecycleOwner) {
        delegate.onPause()
    }

    override fun onStop(owner: LifecycleOwner) {
        delegate.onStop()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        delegate.onDestroy()
    }
}
