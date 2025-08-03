package com.lucasferreiramachado.kcoordinator.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

public interface SetupNavigationCompose {
    public fun setupNavigation(navGraphBuilder: NavGraphBuilder, navHostController: NavHostController)
}