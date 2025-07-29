package com.lucasferreiramachado.kcoordinator.example

import androidx.compose.ui.window.ComposeUIViewController
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.App

fun MainViewController() = ComposeUIViewController { App() }