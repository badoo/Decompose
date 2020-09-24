package com.arkivanov.todo.app.ribs.root

import com.arkivanov.todo.app.ribs.root.RootView.Event
import com.badoo.reaktive.observable.Observable
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory

interface RootView : RibView, Observable<Event> {

    sealed class Event {
        object TodoClicked : Event()
        object SettingsClicked : Event()
    }

    interface Factory : ViewFactory<Nothing?, RootView>
}
