package com.lucasferreiramachado.kcoordinator.example.multiplatform.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun App(
    startDestination: Any = AppNavigationRoute.SplashScreen,
    initialAction: AppCoordinatorAction = AppCoordinatorAction.StartLoginFlow,
) {
    val appCoordinator = AppCoordinator()

    MaterialTheme {
        appCoordinator.start(
            startDestination,
            initialAction = initialAction
        )
    }
}