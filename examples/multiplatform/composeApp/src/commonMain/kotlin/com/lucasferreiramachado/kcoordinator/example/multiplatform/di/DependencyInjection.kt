package com.lucasferreiramachado.kcoordinator.example.multiplatform.di

import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.AuthCoordinator
import com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.home.HomeCoordinator

class AuthCoordinatorFactory {
    fun create(parent: Coordinator<*>): AuthCoordinator = AuthCoordinator(parent)
}
class HomeCoordinatorFactory {
    fun create(parent: Coordinator<*>): HomeCoordinator = HomeCoordinator(parent)
}