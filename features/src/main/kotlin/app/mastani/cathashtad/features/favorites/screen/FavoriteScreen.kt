package app.mastani.cathashtad.features.favorites.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.mastani.cathashtad.designSystem.ErrorView
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.designSystem.loading.Loading
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.R
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.collectInLaunchedEffect
import app.mastani.cathashtad.features.common.use
import app.mastani.cathashtad.features.favorites.screen.component.FavoriteBreed
import app.mastani.cathashtad.features.favorites.viewModel.FavoriteContract
import app.mastani.cathashtad.features.favorites.viewModel.FavoriteViewModel
import kotlinx.collections.immutable.ImmutableList

@Composable
fun FavoriteRoute(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onDisplayMessage: (SnackbarMessage?) -> Unit,
    onNavigateToCatBreedDetail: (String) -> Unit
) {
    val (state, effect, dispatcher) = use(viewModel)

    LaunchedEffect(Unit) {
        dispatcher(FavoriteContract.Event.FetchFavorites)
    }

    effect.collectInLaunchedEffect {
        when (it) {
            is FavoriteContract.Effect.OnDisplayMessage -> {
                onDisplayMessage(SnackbarMessage(it.message))
            }
        }
    }

    FavoriteScreen(
        state = state,
        onNavigateToCatBreedDetail = onNavigateToCatBreedDetail,
        onTryAgain = {
            dispatcher(FavoriteContract.Event.FetchFavorites)
        }
    )
}

@Composable
fun FavoriteScreen(
    state: FavoriteContract.State,
    onNavigateToCatBreedDetail: (String) -> Unit,
    onTryAgain: () -> Unit
) {
    when (state.favorites) {
        is DataState.Success -> FavoriteList(
            breeds = state.favorites.data,
            onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
        )

        is DataState.Loading -> Loading()
        is DataState.Error -> ErrorView(onTryAgain)
    }
}

@Composable
fun FavoriteList(
    breeds: ImmutableList<CatBreedUiModel>,
    onNavigateToCatBreedDetail: (String) -> Unit,
) {
    if (breeds.isEmpty()) {
        EmptyFavoriteList()
    } else {
        LazyColumn {
            items(breeds.size) { index ->
                val breed = breeds[index]
                FavoriteBreed(
                    breed = breed,
                    onClick = onNavigateToCatBreedDetail
                )
            }
        }
    }
}

@Composable
fun EmptyFavoriteList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(R.drawable.lonely_cat),
            contentDescription = "Sad Cat",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )

        Text(
            modifier = Modifier,
            text = stringResource(R.string.favorite_list_is_empty),
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Preview
@Composable
fun FavoriteScreenPrev() {
    FavoriteScreen(
        state = FavoriteContract.State.PREVIEW,
        onNavigateToCatBreedDetail = {},
        onTryAgain = {}
    )
}