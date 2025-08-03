package com.lucasferreiramachado.kcoordinator

public interface CustomKCoordinatorActionHandler<CustomAction: KCoordinatorAction> {
    fun handleCustom(action: CustomAction)
}

public inline fun <reified Action: KCoordinatorAction, reified CustomAction: KCoordinatorAction, Class> Class.trigger(action: KCoordinatorAction)
        where Class: KCoordinatorActionHandler<Action>,
              Class: CustomKCoordinatorActionHandler<CustomAction> {
    when (action) {
        is Action -> {
            handle(action)
        }
        is CustomAction -> {
            handleCustom(action)
        }
        else -> throw IllegalArgumentException("Unsupported action: $action")
    }
}

public fun <CustomAction: KCoordinatorAction> CustomKCoordinatorActionHandler<CustomAction>.triggerCustom(action: CustomAction) {
    handleCustom(action)
}