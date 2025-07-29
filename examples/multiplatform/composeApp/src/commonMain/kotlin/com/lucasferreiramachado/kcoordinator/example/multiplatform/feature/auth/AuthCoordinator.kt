package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.NavigationComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.HomeScreenRoute
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.ui.screens.LoginScreen

sealed class AuthCoordinatorAction: CoordinatorAction {
    data object ShowLoginScreen : AuthCoordinatorAction()
    data class Authenticated(val username: String) : AuthCoordinatorAction()
}

class AuthCoordinator(
    override val parent: Coordinator<*>
) : NavigationComposeKCoordinator<AuthCoordinatorAction> {
    private var navHostController: NavHostController? = null
    override fun handle(action: AuthCoordinatorAction) {
        when (action) {
            is AuthCoordinatorAction.ShowLoginScreen -> {
                navHostController?.navigate("login")
            }
            is AuthCoordinatorAction.Authenticated -> {
                val username = action.username
                navHostController?.popBackStack()
                parent.trigger(AppCoordinatorAction.StartHomeFlow(username = username))
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController
        navGraphBuilder.composable( "login") {
            LoginScreen(
                onUserAuthenticated = { username ->
                    trigger(AuthCoordinatorAction.Authenticated(username = username))
                }
            )
        }
    }
}