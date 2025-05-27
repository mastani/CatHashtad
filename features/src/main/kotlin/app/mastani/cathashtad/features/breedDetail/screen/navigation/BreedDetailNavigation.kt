package app.mastani.cathashtad.features.breedDetail.screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.features.breedDetail.screen.BreedDetailRoute

const val BREED_ID = "breed_id"
const val BREED_DETAIL_ROUTE = "breedDetail?$BREED_ID={$BREED_ID}"

fun NavController.navigateToBreedDetail(breedId: String) = navigate("breedDetail?$BREED_ID=$breedId")

fun NavGraphBuilder.breedDetailScreen(
    onDisplayMessage: (SnackbarMessage?) -> Unit
) {
    composable(
        route = BREED_DETAIL_ROUTE,
        arguments = listOf(
            navArgument(name = BREED_ID) {
                type = NavType.StringType
            }
        )
    ) {
        val breedId = it.arguments?.getString(BREED_ID) ?: return@composable

        BreedDetailRoute(
            onDisplayMessage = onDisplayMessage,
            breedId = breedId
        )
    }
}