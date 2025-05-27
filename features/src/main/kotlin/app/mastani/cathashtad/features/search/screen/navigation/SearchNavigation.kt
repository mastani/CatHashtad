package app.mastani.cathashtad.features.search.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.features.search.screen.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavGraphBuilder.searchScreen(
    onDisplayMessage: (SnackbarMessage?) -> Unit,
    onNavigateToCatBreedDetail: (String) -> Unit
) {
    composable(route = SEARCH_ROUTE) {
        SearchRoute(
            onDisplayMessage = onDisplayMessage,
            onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
        )
    }
}