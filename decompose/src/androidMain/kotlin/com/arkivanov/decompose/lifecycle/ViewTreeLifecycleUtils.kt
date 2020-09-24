package com.arkivanov.decompose.lifecycle

import android.view.View
import com.arkivanov.decompose.R

fun View.setLifecycle(lifecycle: Lifecycle?) {
    setTag(R.id.decompose_view_tree_lifecycle, lifecycle)
}

fun View.getLifecycle(): Lifecycle? {
    var found: Lifecycle? = null
    var view: View? = this
    while ((view != null) && (found == null)) {
        found = view.getTag(R.id.decompose_view_tree_lifecycle) as Lifecycle?
        view = view.parent as? View?
    }

    return found
}

fun View.requireLifecycle(): Lifecycle = requireNotNull(getLifecycle())
