package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.NavigationComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.HomeScreenRoute
import com.lucasferreiramachado.kcoordinator.example.multiplatform.ui.screens.LoginScreen

sealed class AuthCoordinatorAction: CoordinatorAction {
    // TODO("2. Definir as ações")
}

class AuthCoordinator(
    override val parent: Coordinator<*>
) : NavigationComposeKCoordinator<AuthCoordinatorAction> {
    override fun handle(action: AuthCoordinatorAction) {
        // TODO("3. Manipular ações")
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        navGraphBuilder.composable( "login") {
            LoginScreen(
                onUserAuthenticated = { username ->
                    navHostController.popBackStack()
                    navHostController.navigate(HomeScreenRoute(username = username))
                }
            )
        }
    }
}