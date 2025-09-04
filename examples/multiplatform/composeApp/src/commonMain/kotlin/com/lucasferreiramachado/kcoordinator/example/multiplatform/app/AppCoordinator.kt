package com.lucasferreiramachado.kcoordinator.example.multiplatform.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.RootComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers.AuthCallbackHandler
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers.Flow1CallbackHandler
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers.Flow2CallbackHandler
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers.HomeCallbackHandler
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.ui.screens.SplashScreen
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.Flow1Coordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.Flow1CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.Flow2Coordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.Flow2CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.HomeCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.HomeCoordinatorAction
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

sealed class AppNavigationRoute {
    @Serializable data object SplashScreen: AppNavigationRoute()
}

sealed class AppCoordinatorAction: KCoordinatorAction {
    data object StartLoginFlow : AppCoordinatorAction()
    data class StartHomeFlow(val username: String) : AppCoordinatorAction()

    data object StartFeature1Flow1 : AppCoordinatorAction()
    data object StartFeature1Flow2 : AppCoordinatorAction()
}

class AppCoordinator(
) : RootComposeKCoordinator<AppCoordinatorAction> {

    val authCoordinator = AuthCoordinator(AuthCallbackHandler(this))
    val homeCoordinator = HomeCoordinator(HomeCallbackHandler(this))

    val flow1Coordinator: Flow1Coordinator = Flow1Coordinator(
        Flow1CallbackHandler(this)
    )
    val flow2Coordinator: Flow2Coordinator = Flow2Coordinator(
        Flow2CallbackHandler(this)
    )

    override fun handle(action: AppCoordinatorAction) {
        when (action) {
            is AppCoordinatorAction.StartLoginFlow -> {
                authCoordinator.trigger(AuthCoordinatorAction.ShowLoginScreen)
            }
            is AppCoordinatorAction.StartHomeFlow -> {
                val username = action.username
                homeCoordinator.trigger(HomeCoordinatorAction.ShowHomeScreen(username = username))
            }
            is AppCoordinatorAction.StartFeature1Flow1 -> {
                flow1Coordinator.trigger(Flow1CoordinatorAction.ShowScreen1)
            }
            is AppCoordinatorAction.StartFeature1Flow2 -> {
                flow2Coordinator.trigger(Flow2CoordinatorAction.ShowScreen1)
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
        flow1Coordinator.setupNavigation(navGraphBuilder, navHostController)
        flow2Coordinator.setupNavigation(navGraphBuilder, navHostController)

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