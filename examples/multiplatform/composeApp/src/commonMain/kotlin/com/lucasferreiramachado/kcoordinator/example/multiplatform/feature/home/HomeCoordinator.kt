package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lucasferreiramachado.kcoordinator.NavigationComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.HomeScreenRoute
import com.lucasferreiramachado.kcoordinator.example.multiplatform.ui.screens.HomeScreen

sealed class HomeCoordinatorAction: CoordinatorAction {
    // TODO("2. Definir as ações")
}

class HomeCoordinator(
    override val parent: Coordinator<*>
) : NavigationComposeKCoordinator<HomeCoordinatorAction> {
    override fun handle(action: HomeCoordinatorAction) {
        // TODO("3. Manipular ações")
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        navGraphBuilder.composable<HomeScreenRoute> {
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