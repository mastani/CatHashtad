package app.mastani.cathashtad.features.favorites.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.features.favorites.screen.FavoriteRoute

const val FAVORITES_ROUTE = "favorites"

fun NavGraphBuilder.favoritesScreen(
    onDisplayMessage: (SnackbarMessage?) -> Unit,
    onNavigateToCatBreedDetail: (String) -> Unit
) {
    composable(route = FAVORITES_ROUTE) {
        FavoriteRoute(
            onDisplayMessage = onDisplayMessage,
            onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
        )
    }
}