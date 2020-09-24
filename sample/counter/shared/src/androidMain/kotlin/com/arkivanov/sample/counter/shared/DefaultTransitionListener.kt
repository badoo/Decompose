package com.arkivanov.sample.counter.shared

import android.transition.Transition

internal interface DefaultTransitionListener : Transition.TransitionListener {

    override fun onTransitionStart(transition: Transition) {
    }

    override fun onTransitionEnd(transition: Transition) {
    }

    override fun onTransitionCancel(transition: Transition) {
    }

    override fun onTransitionPause(transition: Transition) {
    }

    override fun onTransitionResume(transition: Transition) {
    }
}
