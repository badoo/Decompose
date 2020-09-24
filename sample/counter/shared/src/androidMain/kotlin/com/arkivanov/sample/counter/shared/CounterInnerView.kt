package com.arkivanov.sample.counter.shared

import android.view.View
import android.view.ViewGroup
import com.arkivanov.decompose.renderChild
import com.arkivanov.decompose.addView
import com.arkivanov.decompose.render
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.sample.counter.shared.inner.CounterInnerContainer

@Suppress("FunctionName")
fun ViewGroup.counterInnerView(model: CounterInnerContainer.Model): View {
    val view = addView(R.layout.counter_inner)

    view.renderChild(model.leftChild.map { it.counter }, R.id.container_left, ViewGroup::counterView)
    view.renderChild(model.rightChild.map { it.counter }, R.id.container_right, ViewGroup::counterView)
    view.findViewById<ViewGroup>(R.id.container_counter).counterView(model.counter)

    val leftNextButton = view.findViewById<View>(R.id.button_left_next)
    val leftPrevButton = view.findViewById<View>(R.id.button_left_prev)
    val rightNextButton = view.findViewById<View>(R.id.button_right_next)
    val rightPrevButton = view.findViewById<View>(R.id.button_right_prev)

    leftNextButton.setOnClickListener { model.onNextLeftChild() }
    leftPrevButton.setOnClickListener { model.onPrevLeftChild() }
    rightNextButton.setOnClickListener { model.onNextRightChild() }
    rightPrevButton.setOnClickListener { model.onPrevRightChild() }

    leftPrevButton.render(model.leftChild.map { it.isBackEnabled }, View::setEnabled)
    rightPrevButton.render(model.rightChild.map { it.isBackEnabled }, View::setEnabled)

    return view
}
