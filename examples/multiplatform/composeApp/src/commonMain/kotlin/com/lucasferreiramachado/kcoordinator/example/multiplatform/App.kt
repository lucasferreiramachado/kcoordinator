package com.lucasferreiramachado.kcoordinator.example.multiplatform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.lucasferreiramachado.kcoordinator.example.multiplatform.ui.screens.HomeScreen
import com.lucasferreiramachado.kcoordinator.example.multiplatform.ui.screens.LoginScreen
import com.lucasferreiramachado.kcoordinator.example.multiplatform.ui.screens.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
data class HomeScreenRoute(val username: String)

@Composable
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()

        NavHost(
            startDestination = "splash",
            navController = navHostController
        ) {

            composable("splash") {
                SplashScreen {
                    delay(1500)
                    navHostController.popBackStack()
                    navHostController.navigate("login")
                }
            }

            composable( "login") {
                LoginScreen { username ->
                    navHostController.popBackStack()
                    navHostController.navigate(HomeScreenRoute(username = username))
                }
            }

            composable<HomeScreenRoute> {
                val route = it.toRoute<HomeScreenRoute>()
                HomeScreen(
                    username = route.username,
                    onSignOutButtonPressed = {
                        navHostController.popBackStack()
                        navHostController.navigate("login")
                    }
                )
            }
        }
    }
}