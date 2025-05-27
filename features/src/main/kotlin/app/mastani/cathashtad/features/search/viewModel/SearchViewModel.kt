package app.mastani.cathashtad.features.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mastani.cathashtad.domain.repository.breed.BreedsRepository
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.handleHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val breedsRepository: BreedsRepository
) : ViewModel(), SearchContract {

    private val _userQuery: MutableStateFlow<String> = MutableStateFlow("")

    private val _state = MutableStateFlow(SearchContract.State.EMPTY)
    override val state: StateFlow<SearchContract.State>
        get() = _state.asStateFlow()

    private val effectChannel = Channel<SearchContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<SearchContract.Effect>
        get() = effectChannel.receiveAsFlow()

    override fun event(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.PerformSearch -> {
                viewModelScope.launch {
                    _userQuery.emit(event.query)
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            _userQuery.debounce(500).collectLatest { input ->
                if (input.isEmpty()) return@collectLatest
                performSearch(input)
            }
        }
    }

    private fun performSearch(query: String) {
        fun updateSearchResult(result: DataState<ImmutableList<CatBreedUiModel>>) {
            _state.update { it.copy(searchResults = result) }
        }

        fun fetchSearchResultSuccess(result: List<CatBreedUiModel>) {
            updateSearchResult(DataState.Success(result.toImmutableList()))
        }

        fun fetchSearchResultFailure(throwable: Throwable) {
            updateSearchResult(DataState.Error(throwable.message ?: ""))
            viewModelScope.launch { onApiCallFailure(throwable) }
        }

        updateSearchResult(DataState.Loading)
        viewModelScope.launch {
            breedsRepository.searchCatBreeds(query).fold(
                onSuccess = ::fetchSearchResultSuccess,
                onFailure = ::fetchSearchResultFailure
            )
        }
    }

    private suspend fun onApiCallFailure(ex: Throwable) {
        handleHttpException(
            exception = ex,
            onUnauthorizedException = {
//                effectChannel.send(BreedDetailContract.Effect.OnNavigateToLogin)
            },
            onOtherException = {
                effectChannel.send(SearchContract.Effect.OnDisplayMessage(ex.message))
            }
        )
    }
}