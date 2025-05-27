package app.mastani.cathashtad.features.breedDetail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.mastani.cathashtad.designSystem.ErrorView
import app.mastani.cathashtad.designSystem.NetworkImage
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.designSystem.ext.outOfFive
import app.mastani.cathashtad.designSystem.loading.Loading
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.R
import app.mastani.cathashtad.features.breedDetail.viewModel.BreedDetailContract
import app.mastani.cathashtad.features.breedDetail.viewModel.BreedDetailViewModel
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.collectInLaunchedEffect
import app.mastani.cathashtad.features.common.use
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewState

@Composable
fun BreedDetailRoute(
    viewModel: BreedDetailViewModel = hiltViewModel(),
    onDisplayMessage: (SnackbarMessage?) -> Unit,
    breedId: String
) {
    val (state, effect, dispatcher) = use(viewModel)

    LaunchedEffect(Unit) {
        dispatcher(BreedDetailContract.Event.FetchCatBreed(breedId))
    }

    effect.collectInLaunchedEffect {
        when (it) {
            is BreedDetailContract.Effect.OnDisplayMessage -> {
                onDisplayMessage(SnackbarMessage(it.message))
            }
        }
    }

    BreedDetailScreen(
        state = state,
        onTryAgain = {
            dispatcher(BreedDetailContract.Event.FetchCatBreed(breedId))
        },
        addToFavorite = {
            dispatcher(BreedDetailContract.Event.AddToFavorite(breedId))
        }
    )
}

@Composable
fun BreedDetailScreen(
    state: BreedDetailContract.State,
    onTryAgain: () -> Unit,
    addToFavorite: (String) -> Unit
) {
    when (state.catBreed) {
        is DataState.Success -> BreedDetailContent(
            breed = state.catBreed.data,
            isFavorite = if (state.isFavorite is DataState.Success)
                state.isFavorite.data
            else
                false,
            addToFavorite = addToFavorite
        )

        is DataState.Loading -> Loading()
        is DataState.Error -> ErrorView(onTryAgain)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailContent(
    breed: CatBreedUiModel,
    isFavorite: Boolean,
    addToFavorite: (String) -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 100.dp,
        sheetContent = {
            breed.wikipediaUrl?.let {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(R.string.scroll_to_wikipedia))
                    }

                    if (scaffoldState.bottomSheetState.hasExpandedState) {
                        val state = rememberWebViewState(it)
                        WebView(state)
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            BreedDetailProperties(
                breed = breed,
                isFavorite = isFavorite,
                addToFavorite = addToFavorite
            )
        }
    }
}

@Composable
fun BreedDetailProperties(
    breed: CatBreedUiModel,
    isFavorite: Boolean,
    addToFavorite: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        NetworkImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            url = breed.imageUrl ?: "",
            placeholder = R.drawable.ic_cat,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopStart
        )

        Column(
            modifier = Modifier.padding(horizontal = 18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = breed.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { addToFavorite(breed.id) },
                    painter = if (!isFavorite)
                        painterResource(id = R.drawable.ic_add_favorite)
                    else
                        painterResource(id = R.drawable.ic_is_favorited),
                    contentDescription = "Favorite"
                )
            }

            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = breed.description ?: "",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )

            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = stringResource(R.string.personality),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    PropertyName(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = stringResource(R.string.life_span)
                    )

                    PropertyName(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = stringResource(R.string.alternate_names)
                    )

                    PropertyName(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = stringResource(R.string.temperament)
                    )

                    PropertyName(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = stringResource(R.string.affection_level)
                    )

                    PropertyName(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = stringResource(R.string.child_friendly)
                    )

                    PropertyName(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = stringResource(R.string.intelligence)
                    )
                }

                Spacer(modifier = Modifier.width(28.dp))

                Column {
                    PropertyValue(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = breed.lifeSpan ?: ""
                    )

                    PropertyValue(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = breed.altNames?.ifEmpty { "None" } ?: ""
                    )

                    PropertyValue(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = breed.temperament?.ifEmpty { "None" } ?: ""
                    )

                    PropertyValue(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = breed.affectionLevel.outOfFive()
                    )

                    PropertyValue(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = breed.childFriendly.outOfFive()
                    )

                    PropertyValue(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = breed.intelligence.outOfFive()
                    )
                }
            }
        }
    }
}

@Composable
fun PropertyName(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.outline
        )
    )
}

@Composable
fun PropertyValue(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Preview
@Composable
fun BreedDetailScreenPrev() {
    BreedDetailScreen(
        state = BreedDetailContract.State.PREVIEW,
        onTryAgain = {},
        addToFavorite = {}
    )
}