package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.di.Flow1CoordinatorFactory
import com.lucasferreiramachado.kcoordinator.example.multiplatform.di.Flow2CoordinatorFactory
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.Flow1CoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.Flow2CoordinatorAction

sealed class Feature1CoordinatorAction: KCoordinatorAction {
    data object StartFlow1 : Feature1CoordinatorAction()
    data object StartFlow2 : Feature1CoordinatorAction()
}

class Feature1Coordinator(
    flow1CoordinatorFactory: Flow1CoordinatorFactory = Flow1CoordinatorFactory(),
    flow2CoordinatorFactory: Flow2CoordinatorFactory = Flow2CoordinatorFactory(),
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<Feature1CoordinatorAction> {
    private val flow1Coordinator = flow1CoordinatorFactory.create(parent = this)
    private val flow2Coordinator = flow2CoordinatorFactory.create(parent = this)

    private var navHostController: NavHostController? = null

    override fun handle(action: Feature1CoordinatorAction) {
        when (action) {
            is Feature1CoordinatorAction.StartFlow1 -> {
                flow1Coordinator.trigger(Flow1CoordinatorAction.ShowScreen1)
            }
            is Feature1CoordinatorAction.StartFlow2 -> {
                flow2Coordinator.trigger(Flow2CoordinatorAction.ShowScreen1)
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        flow1Coordinator.setupNavigation(navGraphBuilder, navHostController)
        flow2Coordinator.setupNavigation(navGraphBuilder, navHostController)
    }
}