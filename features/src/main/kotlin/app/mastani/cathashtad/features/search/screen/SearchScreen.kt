package app.mastani.cathashtad.features.search.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import app.mastani.cathashtad.features.R
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.collectInLaunchedEffect
import app.mastani.cathashtad.features.common.use
import app.mastani.cathashtad.features.search.screen.component.SearchItem
import app.mastani.cathashtad.features.search.viewModel.SearchContract
import app.mastani.cathashtad.features.search.viewModel.SearchViewModel

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    onDisplayMessage: (SnackbarMessage?) -> Unit,
    onNavigateToCatBreedDetail: (String) -> Unit
) {
    val (state, effect, dispatcher) = use(viewModel)

    effect.collectInLaunchedEffect {
        when (it) {
            is SearchContract.Effect.OnDisplayMessage -> {
                onDisplayMessage(SnackbarMessage(it.message))
            }
        }
    }

    SearchScreen(
        state = state,
        onNavigateToCatBreedDetail = onNavigateToCatBreedDetail,
        onPerformSearch = {
            dispatcher(SearchContract.Event.PerformSearch(it))
        }
    )
}

@Composable
fun SearchScreen(
    state: SearchContract.State,
    onNavigateToCatBreedDetail: (String) -> Unit,
    onPerformSearch: (String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState()
    ) {
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                value = query,
                onValueChange = {
                    query = it
                    onPerformSearch(it)
                },
                label = { Text(stringResource(R.string.search_query)) }
            )
        }

        if (query.isEmpty()) {
            item {
                EmptyQueryView()
            }
        } else {
            when (state.searchResults) {
                is DataState.Success -> {
                    val results = state.searchResults.data
                    items(results.size) { index ->
                        val breed = results[index]
                        SearchItem(
                            breed = breed,
                            onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
                        )
                    }
                }

                is DataState.Loading -> item { Loading() }
                is DataState.Error -> item { ErrorView(onTryAgain = {}) }
            }
        }
    }
}

@Composable
fun EmptyQueryView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(84.dp),
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "Search",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.type_anything),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        state = SearchContract.State.PREVIEW,
        onNavigateToCatBreedDetail = {},
        onPerformSearch = {}
    )
}