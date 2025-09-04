package com.lucasferreiramachado.kcoordinator

public interface KCoordinator<Action: KCoordinatorAction>: KCoordinatorActionTrigger<KCoordinatorAction>, KCoordinatorActionHandler<Action> {

    public override fun trigger(action: KCoordinatorAction) {
        try {
            @Suppress("UNCHECKED_CAST")
            (action as? Action)?.let { handle(it) }
        } catch (e: ClassCastException) {
            e.printStackTrace()
            throw IllegalArgumentException("Unsupported action: $action")
        }
    }
}