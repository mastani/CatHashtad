package app.mastani.cathashtad.features.favorites.viewModel

import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.UnidirectionalViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface FavoriteContract : UnidirectionalViewModel<FavoriteContract.Event, FavoriteContract.Effect, FavoriteContract.State> {

    sealed interface Event {
        data object FetchFavorites: Event
    }

    sealed interface Effect {
        data class OnDisplayMessage(val message: String?) : Effect
    }

    data class State(
        val favorites: DataState<ImmutableList<CatBreedUiModel>>
    ) {
        companion object {
            val EMPTY = State(
                favorites = DataState.Loading,
            )

            val PREVIEW = State(
                favorites = DataState.Success(
                    persistentListOf(
                        CatBreedUiModel.PREVIEW,
                        CatBreedUiModel.PREVIEW,
                        CatBreedUiModel.PREVIEW
                    )
                )
            )
        }
    }
}