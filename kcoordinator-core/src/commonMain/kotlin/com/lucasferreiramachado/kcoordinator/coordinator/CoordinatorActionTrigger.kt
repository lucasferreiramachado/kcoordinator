package com.lucasferreiramachado.kcoordinator.coordinator

interface CoordinatorActionTrigger<Action: CoordinatorAction> {
    fun trigger(action: Action)
}