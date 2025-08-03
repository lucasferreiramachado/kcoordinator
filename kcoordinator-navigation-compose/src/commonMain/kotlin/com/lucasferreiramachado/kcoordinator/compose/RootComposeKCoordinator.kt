package com.lucasferreiramachado.kcoordinator.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction

public interface RootComposeKCoordinator<Action: KCoordinatorAction>: KCoordinator<Action> {

    @Composable
    public fun start(
        startDestination: Any,
        initialAction: Action
    ) {
        val navHostController = rememberNavController()
        NavHost(
            startDestination = startDestination,
            navController = navHostController
        ) {
            setupNavigation(
                initialAction,
                this,
                navHostController
            )
        }
    }

    fun setupNavigation(
        initialAction: Action,
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    )
}