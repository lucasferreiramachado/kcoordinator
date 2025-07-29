package com.lucasferreiramachado.kcoordinator.example.multiplatform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.ui.screens.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
data class HomeScreenRoute(val username: String)

@Composable
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()
        val appCoordinator = AppCoordinator()
        NavHost(
            startDestination = "splash",
            navController = navHostController
        ) {
            appCoordinator.setupNavigation(this, navHostController)

            composable("splash") {
                SplashScreen (
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