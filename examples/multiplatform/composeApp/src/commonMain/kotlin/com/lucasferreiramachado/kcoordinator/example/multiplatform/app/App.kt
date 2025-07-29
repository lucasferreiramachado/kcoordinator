package com.lucasferreiramachado.kcoordinator.example.multiplatform.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.ui.screens.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

sealed class AppNavigationRoute {
    @Serializable data object SplashScreen: AppNavigationRoute()
}

@Composable
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()
        val appCoordinator = AppCoordinator()
        NavHost(
            startDestination = AppNavigationRoute.SplashScreen,
            navController = navHostController
        ) {
            appCoordinator.setupNavigation(this, navHostController)

            composable<AppNavigationRoute.SplashScreen>() {
                SplashScreen(
                    onSplashScreenLaunched = {
                        delay(1500)
                        navHostController.popBackStack()
                        appCoordinator.trigger(AppCoordinatorAction.StartLoginFlow)
                    }
                )
            }
        }
    }
}