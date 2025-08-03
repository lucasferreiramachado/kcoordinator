package com.lucasferreiramachado.kcoordinator.example.multiplatform.di

import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.AuthCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.HomeCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinator

class AuthCoordinatorFactory {
    fun create(parent: KCoordinator<*>): AuthCoordinator = AuthCoordinator(parent)
}
class HomeCoordinatorFactory {
    fun create(parent: KCoordinator<*>): HomeCoordinator = HomeCoordinator(parent)
}