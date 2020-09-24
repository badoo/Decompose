package com.arkivanov.todo.app.ribs.root

import android.content.Context
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.platform.setContent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.todo.app.ribs.interop.DecomposeComponentBuilder
import com.arkivanov.todo.database.TodoDatabaseDriverFactory
import com.arkivanov.todo.root.TodoRoot
import com.arkivanov.todo.root.invoke

internal class TodoRootComponentBuilder(
    private val context: Context
) : DecomposeComponentBuilder<TodoRoot> {

    override fun createComponent(componentContext: ComponentContext): TodoRoot =
        TodoRoot(componentContext, TodoDatabaseDriverFactory(context))

    override fun attachView(parent: ViewGroup, component: TodoRoot) {
        parent.setContent(Recomposer.current()) {
            MaterialTheme { component.model() }
        }
    }
}
