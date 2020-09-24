package com.arkivanov.sample.counter.shared

import android.transition.AutoTransition
import android.view.View
import android.view.ViewGroup
import com.arkivanov.decompose.addView
import com.arkivanov.decompose.render
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.sample.counter.shared.root.CounterRootContainer

@Suppress("FunctionName")
fun ViewGroup.counterRootView(model: CounterRootContainer.Model): View {
    val view = addView(R.layout.counter_root)

    view.renderChild(model.child.map { it.inner }, R.id.container_child, ::AutoTransition, ViewGroup::counterInnerView)

    view.findViewById<ViewGroup>(R.id.container_counter).counterView(model.counter)

    val nextButton = view.findViewById<View>(R.id.button_next)
    val prevButton = view.findViewById<View>(R.id.button_prev)

    nextButton.setOnClickListener { model.onNextChild() }
    prevButton.setOnClickListener { model.onPrevChild() }

    prevButton.render(model.child.map { it.isBackEnabled }, View::setEnabled)

    return view
}
