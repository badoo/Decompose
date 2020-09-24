package com.arkivanov.todo.app.ribs.root

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.arkivanov.todo.app.R
import com.arkivanov.todo.app.ribs.root.RootView.Event
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.subject.Relay
import com.badoo.reaktive.subject.publish.PublishSubject
import com.badoo.ribs.core.Node

internal class RootViewImpl(
    override val androidView: ViewGroup,
    private val events: Relay<Event> = PublishSubject()
) : RootView, Observable<Event> by events {

    init {
        androidView.findViewById<View>(R.id.button_todo).setOnClickListener { events.onNext(Event.TodoClicked) }
        androidView.findViewById<View>(R.id.button_settings).setOnClickListener { events.onNext(Event.SettingsClicked) }
    }

    override fun getParentViewForChild(child: Node<*>): ViewGroup? =
        androidView.findViewById(R.id.container)

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.root
    ) : RootView.Factory {
        override fun invoke(dependency: Nothing?): (ViewGroup) -> RootView =
            { parent ->
                RootViewImpl(
                    androidView = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false) as ViewGroup
                )
            }
    }
}
