package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.dashboard.DashboardViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.dashboard.composables.DashboardScreen
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.home.HomeUiState
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.home.HomeViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.home.composables.HomeScreen
import kotlinx.serialization.Serializable

sealed class HomeNavigationRoute {
    @Serializable data class HomeScreen(val username: String): HomeNavigationRoute()
    @Serializable object DashboardScreen: HomeNavigationRoute()
}

sealed class HomeCoordinatorAction: KCoordinatorAction {
    data class ShowHomeScreen(val username: String) : HomeCoordinatorAction()
    data object ShowDashboardScreen : HomeCoordinatorAction()
    data object SignOut : HomeCoordinatorAction()
    data object GoBack : HomeCoordinatorAction()
}

class HomeCoordinator(
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<HomeCoordinatorAction> {

    private var navHostController: NavHostController? = null

    override fun handle(action: HomeCoordinatorAction) {
        when (action) {
            is HomeCoordinatorAction.ShowHomeScreen -> {
                val username = action.username
                navHostController?.popBackStack()
                navHostController?.navigate(HomeNavigationRoute.HomeScreen(username = username))
            }
            is HomeCoordinatorAction.ShowDashboardScreen -> {
                navHostController?.navigate(HomeNavigationRoute.DashboardScreen)
            }
            is HomeCoordinatorAction.SignOut -> {
                navHostController?.popBackStack()
                parent.trigger(AppCoordinatorAction.StartLoginFlow)
            }
            is HomeCoordinatorAction.GoBack -> {
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

            composable<HomeNavigationRoute.HomeScreen> {
                val route = it.toRoute<HomeNavigationRoute.HomeScreen>()

                val initialState: HomeUiState = HomeUiState(
                    username = route.username
                )
                val viewModel = HomeViewModel(initialState)
                viewModel.coordinator = this@HomeCoordinator
                HomeScreen(viewModel)
            }

            composable<HomeNavigationRoute.DashboardScreen> {
                val viewModel = DashboardViewModel()
                viewModel.coordinator = this@HomeCoordinator
                DashboardScreen(viewModel)
            }
        }
    }
}