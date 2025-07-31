package com.lucasferreiramachado.kcoordinator.example
import androidx.compose.ui.unit.DpSize

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.App

fun main() = application {
    Window(
        state = WindowState(
            size = DpSize(width = 400.dp, height = 800.dp),
        ),
        onCloseRequest = ::exitApplication,
        title = "KCoordinator Example"
    ) {
        App()
    }
}