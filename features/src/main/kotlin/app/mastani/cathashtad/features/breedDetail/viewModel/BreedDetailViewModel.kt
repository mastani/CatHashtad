package app.mastani.cathashtad.features.breedDetail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mastani.cathashtad.domain.repository.breed.BreedsRepository
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.domain.repository.favorite.FavoriteRepository
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.handleHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    private val breedsRepository: BreedsRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel(), BreedDetailContract {

    private val _state = MutableStateFlow(BreedDetailContract.State.EMPTY)
    override val state: StateFlow<BreedDetailContract.State>
        get() = _state.asStateFlow()

    private val effectChannel = Channel<BreedDetailContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<BreedDetailContract.Effect>
        get() = effectChannel.receiveAsFlow()

    override fun event(event: BreedDetailContract.Event) {
        when (event) {
            is BreedDetailContract.Event.FetchCatBreed -> {
                fetchCatBreed(event.id)
                fetchFavoriteState(event.id)
            }

            is BreedDetailContract.Event.AddToFavorite -> {
                addToFavorite(event.id)
            }
        }
    }

    private fun fetchCatBreed(id: String) {
        fun updateCatBreed(catBreed: DataState<CatBreedUiModel>) {
            _state.update { it.copy(catBreed = catBreed) }
        }

        fun fetchCatBreedSuccess(catBreed: CatBreedUiModel) {
            updateCatBreed(DataState.Success(catBreed))
        }

        fun fetchCatBreedFailure(throwable: Throwable) {
            updateCatBreed(DataState.Error(throwable.message ?: ""))
            viewModelScope.launch { onApiCallFailure(throwable) }
        }

        updateCatBreed(DataState.Loading)
        viewModelScope.launch {
            breedsRepository.fetchCatBreedDetail(id).fold(
                onSuccess = ::fetchCatBreedSuccess,
                onFailure = ::fetchCatBreedFailure
            )
        }
    }

    private fun fetchFavoriteState(id: String) {
        fun updateFavorite(isFavorite: Boolean) {
            _state.update { it.copy(isFavorite = DataState.Success(isFavorite)) }
        }

        viewModelScope.launch {
            updateFavorite(favoriteRepository.isFavorite(id))
        }
    }

    private fun addToFavorite(id: String) {
        fun updateFavorite(isFavorite: Boolean) {
            _state.update { it.copy(isFavorite = DataState.Success(isFavorite)) }
        }

        viewModelScope.launch {
            if (favoriteRepository.isFavorite(id)) {
                favoriteRepository.removeFromFavorite(id)
                updateFavorite(false)
            } else {
                favoriteRepository.addToFavorite(id)
                updateFavorite(true)
            }
        }
    }

    private suspend fun onApiCallFailure(ex: Throwable) {
        handleHttpException(
            exception = ex,
            onUnauthorizedException = {
//                effectChannel.send(BreedDetailContract.Effect.OnNavigateToLogin)
            },
            onOtherException = {
                effectChannel.send(BreedDetailContract.Effect.OnDisplayMessage(ex.message))
            }
        )
    }
}