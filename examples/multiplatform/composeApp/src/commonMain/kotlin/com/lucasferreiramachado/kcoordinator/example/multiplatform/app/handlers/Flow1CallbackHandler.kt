package com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers

import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.Flow1CoordinatorCallback

class Flow1CallbackHandler(
    val parent: AppCoordinator
): Flow1CoordinatorCallback {

    override fun starFlow2() {
        parent.trigger(AppCoordinatorAction.StartFeature1Flow2)
    }
}