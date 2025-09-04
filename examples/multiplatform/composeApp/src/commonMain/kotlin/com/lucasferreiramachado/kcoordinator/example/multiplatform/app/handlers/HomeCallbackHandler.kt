package com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers

import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.HomeCoordinatorCallback

class HomeCallbackHandler(
    val parent: AppCoordinator
): HomeCoordinatorCallback {

    override fun startLoginFlow() {
        parent.trigger(AppCoordinatorAction.StartLoginFlow)
    }

    override fun startFeature1Flow1() {
        parent.trigger(AppCoordinatorAction.StartFeature1Flow1)
    }

    override fun startFeature1Flow2() {
        parent.trigger(AppCoordinatorAction.StartFeature1Flow2)
    }
}