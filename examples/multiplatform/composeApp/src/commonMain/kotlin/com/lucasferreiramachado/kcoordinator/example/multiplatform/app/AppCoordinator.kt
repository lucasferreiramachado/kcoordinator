package com.lucasferreiramachado.kcoordinator.example.multiplatform.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kcoordinator.NavigationComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.di.AuthCoordinatorFactory
import com.lucasferreiramachado.kcoordinator.example.multiplatform.di.HomeCoordinatorFactory

sealed class AppCoordinatorAction: CoordinatorAction {
    data object StartLoginFlow : AppCoordinatorAction()
    data class StartHomeFlow(val username: String) : AppCoordinatorAction()
}

class AppCoordinator(
    authCoordinatorFactory: AuthCoordinatorFactory = AuthCoordinatorFactory(),
    homeCoordinatorFactory: HomeCoordinatorFactory = HomeCoordinatorFactory(),
    override val parent: Coordinator<*>? = null
) : NavigationComposeKCoordinator<AppCoordinatorAction> {
    private val authCoordinator = authCoordinatorFactory.create(parent = this)
    private val homeCoordinator = homeCoordinatorFactory.create(parent = this)
    override fun handle(action: AppCoordinatorAction) {
        // TODO("4. Manipular ações")
    }

    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        authCoordinator.setupNavigation(navGraphBuilder, navHostController)
        homeCoordinator.setupNavigation(navGraphBuilder, navHostController)
    }
}