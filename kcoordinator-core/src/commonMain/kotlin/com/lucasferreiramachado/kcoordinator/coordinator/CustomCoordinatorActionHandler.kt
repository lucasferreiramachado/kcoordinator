package com.lucasferreiramachado.kcoordinator.coordinator

interface CustomCoordinatorActionHandler<CustomAction: CoordinatorAction> {
    fun handleCustom(action: CustomAction)
}

inline fun <reified Action: CoordinatorAction, reified CustomAction: CoordinatorAction, Class> Class.trigger(action: CoordinatorAction)
        where Class: CoordinatorActionHandler<Action>,
              Class: CustomCoordinatorActionHandler<CustomAction> {
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

fun <CustomAction: CoordinatorAction> CustomCoordinatorActionHandler<CustomAction>.triggerCustom(action: CustomAction) {
    handleCustom(action)
}