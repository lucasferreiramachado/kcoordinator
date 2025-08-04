package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.Feature1CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen1.Screen1ViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen1.composables.Screen1
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2.Screen2ViewModel
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2.composables.Screen2
import kotlinx.serialization.Serializable

sealed class Flow2NavigationRoute {
    @Serializable object Screen1: Flow2NavigationRoute()
    @Serializable object Screen2: Flow2NavigationRoute()
}

sealed class Flow2CoordinatorAction: KCoordinatorAction {
    data object ShowScreen1: Flow2CoordinatorAction()
    data object ShowScreen2 : Flow2CoordinatorAction()

    data object StarFlow1 : Flow2CoordinatorAction()

    data object GoBack : Flow2CoordinatorAction()
}

class Flow2Coordinator(
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<Flow2CoordinatorAction> {

    private var navHostController: NavHostController? = null

    override fun handle(action: Flow2CoordinatorAction) {
        when (action) {
            is Flow2CoordinatorAction.ShowScreen1 -> {
                navHostController?.navigate(Flow2NavigationRoute.Screen1)
            }
            is Flow2CoordinatorAction.ShowScreen2 -> {
                navHostController?.navigate(Flow2NavigationRoute.Screen2)
            }
            is Flow2CoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }
            is Flow2CoordinatorAction.StarFlow1 -> {
                navHostController?.popBackStack()
                parent.trigger(Feature1CoordinatorAction.StartFlow1)
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {

            composable<Flow2NavigationRoute.Screen1> {
                val viewModel = Screen1ViewModel(coordinator = this@Flow2Coordinator)
                Screen1(viewModel)
            }

            composable<Flow2NavigationRoute.Screen2> {
                val viewModel = Screen2ViewModel(coordinator = this@Flow2Coordinator)
                Screen2(viewModel)
            }
        }
    }
}