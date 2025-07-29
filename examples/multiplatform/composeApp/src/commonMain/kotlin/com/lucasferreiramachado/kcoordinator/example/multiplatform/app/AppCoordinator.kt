package com.lucasferreiramachado.kcoordinator.example.multiplatform.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kcoordinator.NavigationComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction

sealed class AppCoordinatorAction: CoordinatorAction {
    // TODO("3. Definir as ações")
}

class AppCoordinator(
    // TODO("2. Injetar as dependências")
    override val parent: Coordinator<*>? = null
) : NavigationComposeKCoordinator<AppCoordinatorAction> {
    override fun handle(action: AppCoordinatorAction) {
        // TODO("4. Manipular ações")
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        // TODO("1. Configurar a navegação Compose")
    }
}