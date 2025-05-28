package app.mastani.cathashtad.features.home.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import app.mastani.cathashtad.designSystem.ErrorView
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.designSystem.loading.Loading
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.common.collectInLaunchedEffect
import app.mastani.cathashtad.features.common.use
import app.mastani.cathashtad.features.home.screen.component.CatBreed
import app.mastani.cathashtad.features.home.viewModel.HomeContract
import app.mastani.cathashtad.features.home.viewModel.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onDisplayMessage: (SnackbarMessage?) -> Unit,
    onNavigateToCatBreedDetail: (String) -> Unit
) {
    val (state, effect, dispatcher) = use(viewModel)

    effect.collectInLaunchedEffect {
        when (it) {
            is HomeContract.Effect.OnDisplayMessage -> {
                onDisplayMessage(SnackbarMessage(it.message))
            }
        }
    }

    val catBreeds = viewModel.catBreedsFlow.collectAsLazyPagingItems()

    BreedList(
        breeds = catBreeds,
        onNavigateToCatBreedDetail = onNavigateToCatBreedDetail,
        onRetry = {
            catBreeds.refresh()
        }
    )
}

@Composable
fun BreedList(
    breeds: LazyPagingItems<CatBreedUiModel>,
    onNavigateToCatBreedDetail: (String) -> Unit,
    onRetry: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(breeds.itemCount) { index ->
            val breed = breeds[index]
            breed?.let {
                CatBreed(
                    breed = it,
                    onClick = onNavigateToCatBreedDetail
                )
            }
        }

        breeds.apply {
            when {
                // initial loading UI
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Loading()
                    }
                }

                // load more UI
                loadState.append is LoadState.Loading -> {
                    item {
                        Loading()
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorView(
                            modifier = Modifier.fillMaxSize(),
                            onTryAgain = onRetry
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BreedListPrev() {
    val fakeData = List(5) { CatBreedUiModel.PREVIEW }
    val pagingData = PagingData.from(fakeData)
    val fakeDataFlow = MutableStateFlow(pagingData)

    BreedList(
        breeds = fakeDataFlow.collectAsLazyPagingItems(),
        onNavigateToCatBreedDetail = {},
        onRetry = {}
    )
}