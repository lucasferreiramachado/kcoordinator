package com.lucasferreiramachado.kcoordinator

public interface KCoordinatorActionTrigger<Action: KCoordinatorAction> {
    public fun trigger(action: Action)
}