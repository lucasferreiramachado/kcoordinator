package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.login.ui.screens.login.LoginScreen
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.login.LoginViewModel
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.forgotpassword.ForgotPasswordViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.forgotpassword.composables.ForgotPasswordScreen
import kotlinx.serialization.Serializable

sealed class AuthNavigationRoute {
    @Serializable data object LoginScreen: AuthNavigationRoute()

    @Serializable data object  ForgotPasswordScreen: AuthNavigationRoute()
}

sealed class AuthCoordinatorAction: KCoordinatorAction {
    data object ShowLoginScreen : AuthCoordinatorAction()
    data object ShowForgotPasswordScreen : AuthCoordinatorAction()
    data class Authenticated(val username: String) : AuthCoordinatorAction()
    data object GoBack : AuthCoordinatorAction()
}

class AuthCoordinator(
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<AuthCoordinatorAction> {

    private var navHostController: NavHostController? = null

    override fun handle(action: AuthCoordinatorAction) {
        when (action) {
            is AuthCoordinatorAction.ShowLoginScreen -> {
                navHostController?.navigate(AuthNavigationRoute.LoginScreen)
            }
            is AuthCoordinatorAction.ShowForgotPasswordScreen -> {
                navHostController?.navigate(AuthNavigationRoute.ForgotPasswordScreen)
            }
            is AuthCoordinatorAction.Authenticated -> {
                val username = action.username
                navHostController?.popBackStack()
                parent.trigger(AppCoordinatorAction.StartHomeFlow(username = username))
            }
            is AuthCoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {

            composable<AuthNavigationRoute.LoginScreen> {
                val viewModel = LoginViewModel()
                viewModel.coordinator = this@AuthCoordinator
                LoginScreen(viewModel)
            }

            composable<AuthNavigationRoute.ForgotPasswordScreen> {
                val viewModel = ForgotPasswordViewModel()
                viewModel.coordinator = this@AuthCoordinator
                ForgotPasswordScreen(viewModel)
            }
        }
    }
}