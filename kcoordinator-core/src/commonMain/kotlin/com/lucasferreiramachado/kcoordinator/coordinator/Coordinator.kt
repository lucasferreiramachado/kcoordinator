package com.lucasferreiramachado.kcoordinator.coordinator

interface Coordinator<Action: CoordinatorAction>: CoordinatorActionTrigger<CoordinatorAction>, CoordinatorActionHandler<Action> {

    val parent: Coordinator<*>?

    override fun trigger(action: CoordinatorAction) {
        try {
            @Suppress("UNCHECKED_CAST")
            (action as? Action)?.let { handle(it) }
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("Unsupported action: $action")
        }
    }
}