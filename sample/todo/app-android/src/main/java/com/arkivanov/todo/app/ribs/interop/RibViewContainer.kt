package com.arkivanov.todo.app.ribs.interop

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import com.badoo.ribs.core.view.RibView

internal class RibViewContainer(context: Context) : RibView {

    override val androidView: ViewGroup = FrameLayout(context)
}
