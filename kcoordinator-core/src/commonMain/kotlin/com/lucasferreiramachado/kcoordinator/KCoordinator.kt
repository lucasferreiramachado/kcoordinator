package com.lucasferreiramachado.kcoordinator

public interface KCoordinator<Action: KCoordinatorAction>: KCoordinatorActionTrigger<KCoordinatorAction>, KCoordinatorActionHandler<Action> {

    public val parent: KCoordinator<*>?

    public override fun trigger(action: KCoordinatorAction) {
        try {
            @Suppress("UNCHECKED_CAST")
            (action as? Action)?.let { handle(it) }
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("Unsupported action: $action")
        }
    }
}