package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen1.Screen1ViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen1.composables.Screen1
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen2.Screen2ViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen2.composables.Screen2
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen3.Screen3ViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.ui.screens.screen3.composables.Screen3
import kotlinx.serialization.Serializable

sealed class Flow1NavigationRoute {
    @Serializable object Screen1: Flow1NavigationRoute()
    @Serializable object Screen2: Flow1NavigationRoute()
    @Serializable object Screen3: Flow1NavigationRoute()
}

sealed class Flow1CoordinatorAction: KCoordinatorAction {
    data object ShowScreen1: Flow1CoordinatorAction()
    data object ShowScreen2 : Flow1CoordinatorAction()
    data object ShowScreen3 : Flow1CoordinatorAction()

    data object StarFlow2 : Flow1CoordinatorAction()

    data object GoBack : Flow1CoordinatorAction()
}

class Flow1Coordinator(
    val callback: Flow1CoordinatorCallback,
): ComposeKCoordinator<Flow1CoordinatorAction> {

    private var navHostController: NavHostController? = null

    override fun handle(action: Flow1CoordinatorAction) {
        when (action) {
            is Flow1CoordinatorAction.ShowScreen1 -> {
                navHostController?.navigate(Flow1NavigationRoute.Screen1)
            }
            is Flow1CoordinatorAction.ShowScreen2 -> {
                navHostController?.navigate(Flow1NavigationRoute.Screen2)
            }
            is Flow1CoordinatorAction.ShowScreen3 -> {
                navHostController?.navigate(Flow1NavigationRoute.Screen3)
            }
            is Flow1CoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }
            is Flow1CoordinatorAction.StarFlow2 -> {
                navHostController?.popBackStack()
                callback.starFlow2()
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {

            composable<Flow1NavigationRoute.Screen1> {
                val viewModel = Screen1ViewModel(coordinator = this@Flow1Coordinator)
                Screen1(viewModel)
            }

            composable<Flow1NavigationRoute.Screen2> {
                val viewModel = Screen2ViewModel(coordinator = this@Flow1Coordinator)
                Screen2(viewModel)
            }

            composable<Flow1NavigationRoute.Screen3> {
                val viewModel = Screen3ViewModel(coordinator = this@Flow1Coordinator)
                Screen3(viewModel)
            }
        }
    }
}