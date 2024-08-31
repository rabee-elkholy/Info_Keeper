package com.androdu.infokeeper.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androdu.infokeeper.ui.info_list_screen.compose.InfoListScreenRoot
import com.androdu.infokeeper.ui.info_screen.compose.InfoScreenRoot

/**
 * Sets up the navigation graph for the application.
 *
 * @param modifier Optional [Modifier] to apply to the [NavHost].
 * @param navController [NavHostController] used for navigating between destinations.
 * @param startDestination The initial destination of the navigation graph. Defaults to [AppDestinations.INFO_SCREEN].
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = AppDestinations.INFO_SCREEN.name,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = AppDestinations.INFO_SCREEN.name,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            InfoScreenRoot(
                onNavigateToInfoList = {
                    navController.navigate(AppDestinations.INFO_LIST_SCREEN.name) {
                        restoreState = true
                    }
                }
            )
        }

        composable(
            route = AppDestinations.INFO_LIST_SCREEN.name,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            InfoListScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

/**
 * Enumeration of the application's navigation destinations.
 */
enum class AppDestinations {
    INFO_SCREEN, // The destination for the Info Screen.
    INFO_LIST_SCREEN // The destination for the Info List Screen.
}

/**
 * Transition when entering a new screen.
 */
val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) = {
    slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
}

/**
 * Transition when exiting the current screen.
 */
val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) = {
    slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
}

/**
 * Transition when re-entering a screen after popping from the back stack.
 */
val popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) = {
    slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
}

/**
 * Transition when exiting a screen to go back in the back stack.
 */
val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) = {
    slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
}
