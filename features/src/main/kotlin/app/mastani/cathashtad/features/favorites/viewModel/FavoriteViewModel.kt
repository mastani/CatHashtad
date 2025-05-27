package app.mastani.cathashtad.features.favorites.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.domain.repository.favorite.FavoriteRepository
import app.mastani.cathashtad.features.common.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel(), FavoriteContract {

    private val _state = MutableStateFlow(FavoriteContract.State.EMPTY)
    override val state: StateFlow<FavoriteContract.State>
        get() = _state.asStateFlow()

    private val effectChannel = Channel<FavoriteContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<FavoriteContract.Effect>
        get() = effectChannel.receiveAsFlow()

    override fun event(event: FavoriteContract.Event) {
        when (event) {
            is FavoriteContract.Event.FetchFavorites -> fetchFavorites()
        }
    }

    private fun fetchFavorites() {
        fun updateFavorites(favorites: DataState<ImmutableList<CatBreedUiModel>>) {
            _state.update { it.copy(favorites = favorites) }
        }

        fun fetchFavoritesSuccess(favorites: List<CatBreedUiModel>) {
            updateFavorites(DataState.Success(favorites.toImmutableList()))
        }

        updateFavorites(DataState.Loading)
        viewModelScope.launch {
            fetchFavoritesSuccess(favoriteRepository.getAll())
        }
    }
}