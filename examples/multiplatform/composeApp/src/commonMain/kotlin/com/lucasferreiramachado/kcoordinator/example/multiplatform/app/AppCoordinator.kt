package com.lucasferreiramachado.kcoordinator.example.multiplatform.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.*
import com.lucasferreiramachado.kcoordinator.compose.RootComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.ui.screens.SplashScreen
import com.lucasferreiramachado.kcoordinator.example.multiplatform.di.AuthCoordinatorFactory
import com.lucasferreiramachado.kcoordinator.example.multiplatform.di.HomeCoordinatorFactory
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.HomeCoordinatorAction
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

sealed class AppNavigationRoute {
    @Serializable data object SplashScreen: AppNavigationRoute()
}

sealed class AppCoordinatorAction: KCoordinatorAction {
    data object StartLoginFlow : AppCoordinatorAction()
    data class StartHomeFlow(val username: String) : AppCoordinatorAction()
}

class AppCoordinator(
    authCoordinatorFactory: AuthCoordinatorFactory = AuthCoordinatorFactory(),
    homeCoordinatorFactory: HomeCoordinatorFactory = HomeCoordinatorFactory(),
    override val parent: KCoordinator<*>? = null
) : RootComposeKCoordinator<AppCoordinatorAction> {

    private val authCoordinator = authCoordinatorFactory.create(parent = this)
    private val homeCoordinator = homeCoordinatorFactory.create(parent = this)

    override fun handle(action: AppCoordinatorAction) {
        when (action) {
            is AppCoordinatorAction.StartLoginFlow -> {
                authCoordinator.trigger(AuthCoordinatorAction.ShowLoginScreen)
            }
            is AppCoordinatorAction.StartHomeFlow -> {
                val username = action.username
                homeCoordinator.trigger(HomeCoordinatorAction.ShowHomeScreen(username = username))
            }
        }
    }

    override fun setupNavigation(
        initialAction: AppCoordinatorAction,
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
    ) {
        authCoordinator.setupNavigation(navGraphBuilder, navHostController)
        homeCoordinator.setupNavigation(navGraphBuilder, navHostController)

        navGraphBuilder.composable<AppNavigationRoute.SplashScreen>() {
            SplashScreen(
                onSplashScreenLaunched = {
                    delay(1500)
                    navHostController.popBackStack()
                    trigger(initialAction)
                }
            )
        }
    }
}