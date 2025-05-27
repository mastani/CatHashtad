package app.mastani.cathashtad.features.search.viewModel

import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.UnidirectionalViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface SearchContract : UnidirectionalViewModel<SearchContract.Event, SearchContract.Effect, SearchContract.State> {

    sealed interface Event {
        data class PerformSearch(val query: String) : Event
    }

    sealed interface Effect {
        data class OnDisplayMessage(val message: String?) : Effect
    }

    data class State(
        val searchResults: DataState<ImmutableList<CatBreedUiModel>>
    ) {
        companion object {
            val EMPTY = State(
                searchResults = DataState.Loading,
            )

            val PREVIEW = State(
                searchResults = DataState.Success(
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