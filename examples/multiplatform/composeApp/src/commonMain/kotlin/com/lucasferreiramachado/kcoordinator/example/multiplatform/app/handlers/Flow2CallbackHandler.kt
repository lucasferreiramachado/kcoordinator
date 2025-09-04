package com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers

import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.Flow2CoordinatorCallback

class Flow2CallbackHandler(
    val parent: AppCoordinator
): Flow2CoordinatorCallback {

    override fun starFlow1() {
        parent.trigger(AppCoordinatorAction.StartFeature1Flow1)
    }
}