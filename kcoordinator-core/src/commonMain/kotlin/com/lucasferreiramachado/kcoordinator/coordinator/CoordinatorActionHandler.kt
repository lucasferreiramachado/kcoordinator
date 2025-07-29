package com.lucasferreiramachado.kcoordinator.coordinator

interface CoordinatorActionHandler<Action: CoordinatorAction> {
    fun handle(action: Action)
}

inline fun <reified Action: CoordinatorAction, Class> Class.trigger(action: CoordinatorAction)
        where Class: CoordinatorActionHandler<Action> {
    when (action) {
        is Action -> {
            handle(action)
        }
        else -> throw IllegalArgumentException("Unsupported action: $action")
    }
}