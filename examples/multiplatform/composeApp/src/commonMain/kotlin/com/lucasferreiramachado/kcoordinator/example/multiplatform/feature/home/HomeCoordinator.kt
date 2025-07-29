package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kcoordinator.NavigationComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction

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
        // TODO("1. Configurar a navegação Compose")
    }
}