package com.lucasferreiramachado.kcoordinator.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface SetupNavigationCompose {
    fun setupNavigation(navGraphBuilder: NavGraphBuilder, navHostController: NavHostController)
}