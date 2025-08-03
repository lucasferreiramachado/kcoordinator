package com.lucasferreiramachado.kcoordinator

public interface KCoordinatorActionHandler<Action: KCoordinatorAction> {
    fun handle(action: Action)
}

public inline fun <reified Action: KCoordinatorAction, Class> Class.trigger(action: KCoordinatorAction)
        where Class: KCoordinatorActionHandler<Action> {
    when (action) {
        is Action -> {
            handle(action)
        }
        else -> throw IllegalArgumentException("Unsupported action: $action")
    }
}