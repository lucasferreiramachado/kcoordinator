package com.lucasferreiramachado.kcoordinator.example.multiplatform.di

import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers.AuthCallbackHandler
import com.lucasferreiramachado.kcoordinator.example.multiplatform.app.handlers.HomeCallbackHandler
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinatorCallback
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.Feature1Coordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow1.Flow1Coordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.Flow2Coordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.HomeCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.HomeCoordinatorCallback

class AuthCoordinatorFactory {
    fun create(
        callbackFactory: AuthCoordinatorCallbackFactory = AuthCoordinatorCallbackFactory(),
        parent: KCoordinator<*>,
    ): AuthCoordinator = AuthCoordinator(
        callback = callbackFactory.create(parent),
        parent
    )
}

class AuthCoordinatorCallbackFactory {
    fun create(parent: KCoordinator<*>): AuthCoordinatorCallback = AuthCallbackHandler(parent)
}

class HomeCoordinatorFactory {
    fun create(
        callbackFactory: HomeCoordinatorCallbackFactory = HomeCoordinatorCallbackFactory(),
        parent: KCoordinator<*>
    ): HomeCoordinator = HomeCoordinator(
        callbackFactory.create(parent),
        parent
    )
}

class HomeCoordinatorCallbackFactory {
    fun create(parent: KCoordinator<*>): HomeCoordinatorCallback = HomeCallbackHandler(parent)
}


class Feature1CoordinatorFactory {
    fun create(parent: KCoordinator<*>): Feature1Coordinator = Feature1Coordinator(parent = parent)
}

class Flow1CoordinatorFactory {
    fun create(parent: KCoordinator<*>): Flow1Coordinator = Flow1Coordinator(parent)
}

class Flow2CoordinatorFactory {
    fun create(parent: KCoordinator<*>): Flow2Coordinator = Flow2Coordinator(parent)
}