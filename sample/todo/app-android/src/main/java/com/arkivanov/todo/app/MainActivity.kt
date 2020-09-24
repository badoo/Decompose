package com.arkivanov.todo.app

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.arkivanov.todo.app.ribs.root.Root
import com.arkivanov.todo.app.ribs.root.RootBuilder
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.builder.BuildContext.Companion.root

class MainActivity : RibActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.main_activity)

        super.onCreate(savedInstanceState)
    }

    override val rootViewGroup: ViewGroup get() = findViewById(R.id.activity_content)

    override fun createRib(savedInstanceState: Bundle?): Rib =
        RootBuilder(
            object : Root.Dependency {
                override val context: Context get() = this@MainActivity
            }
        ).build(root(savedInstanceState))
}
