package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lucasferreiramachado.kcoordinator.NavigationComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.home.ui.screens.HomeScreen
import kotlinx.serialization.Serializable

sealed class HomeNavigationRoute {
    @Serializable data class HomeScreen(val username: String): HomeNavigationRoute()
}

sealed class HomeCoordinatorAction: CoordinatorAction {
    data class ShowHomeScreen(val username: String) : HomeCoordinatorAction()
    data object SignOut : HomeCoordinatorAction()
}

class HomeCoordinator(
    override val parent: Coordinator<*>
) : NavigationComposeKCoordinator<HomeCoordinatorAction> {

    private var navHostController: NavHostController? = null

    override fun handle(action: HomeCoordinatorAction) {
        when (action) {
            is HomeCoordinatorAction.ShowHomeScreen -> {
                val username = action.username
                navHostController?.popBackStack()
                navHostController?.navigate(HomeNavigationRoute.HomeScreen(username = username))
            }
            is HomeCoordinatorAction.SignOut -> {
                navHostController?.popBackStack()
                parent.trigger(AppCoordinatorAction.StartLoginFlow)
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.composable<HomeNavigationRoute.HomeScreen> {
            val route = it.toRoute<HomeNavigationRoute.HomeScreen>()
            HomeScreen(
                username = route.username,
                onSignOutButtonPressed = {
                    trigger(HomeCoordinatorAction.SignOut)
                }
            )
        }
    }
}