package com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers

import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinatorCallback

class AuthCallbackHandler(
    val parent: KCoordinator<*>
): AuthCoordinatorCallback {

    override fun onUserAuthenticated(username: String) {
        parent.trigger(AppCoordinatorAction.StartHomeFlow(username))
    }
}