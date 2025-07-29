package com.lucasferreiramachado.kcoordinator.example.multiplatform.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kcoordinator.NavigationComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.AuthCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.home.HomeCoordinator

sealed class AppCoordinatorAction: CoordinatorAction {
    // TODO("3. Definir as ações")
}

class AppCoordinator(
    // TODO("2. Injetar as dependências")
    override val parent: Coordinator<*>? = null
) : NavigationComposeKCoordinator<AppCoordinatorAction> {
    private val authCoordinator = AuthCoordinator(parent = this)
    private val homeCoordinator = HomeCoordinator(parent = this)
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