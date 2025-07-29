package com.lucasferreiramachado.kcoordinator.example

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.lucasferreiramachado.kcoordinator.example.multiplatform.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KCoordinator Example",
    ) {
        App()
    }
}