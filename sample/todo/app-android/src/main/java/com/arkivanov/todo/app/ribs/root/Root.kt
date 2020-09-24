package com.arkivanov.todo.app.ribs.root

import android.content.Context
import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation

interface Root : Rib {

    interface Dependency {
        val context: Context
    }

    class Customisation(
        val viewFactory: RootView.Factory = RootViewImpl.Factory()
    ) : RibCustomisation
}
