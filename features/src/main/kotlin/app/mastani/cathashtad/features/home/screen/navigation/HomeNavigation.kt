package app.mastani.cathashtad.features.home.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.features.home.screen.HomeRoute

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(
    onDisplayMessage: (SnackbarMessage?) -> Unit,
    onNavigateToCatBreedDetail: (String) -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            onDisplayMessage = onDisplayMessage,
            onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
        )
    }
}