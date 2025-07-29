package com.lucasferreiramachado.kcoordinator

import com.lucasferreiramachado.kcoordinator.coordinator.Coordinator
import com.lucasferreiramachado.kcoordinator.coordinator.CoordinatorAction

interface KCoordinator<Action: CoordinatorAction>: Coordinator<Action>