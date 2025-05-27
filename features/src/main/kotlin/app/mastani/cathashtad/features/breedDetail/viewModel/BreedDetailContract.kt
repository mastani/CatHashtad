package app.mastani.cathashtad.features.breedDetail.viewModel

import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.UnidirectionalViewModel

interface BreedDetailContract :
    UnidirectionalViewModel<BreedDetailContract.Event, BreedDetailContract.Effect, BreedDetailContract.State> {

    sealed interface Event {
        data class FetchCatBreed(val id: String) : Event
        data class AddToFavorite(val id: String) : Event
    }

    sealed interface Effect {
        data class OnDisplayMessage(val message: String?) : Effect
    }

    data class State(
        val catBreed: DataState<CatBreedUiModel>,
        val isFavorite: DataState<Boolean>
    ) {
        companion object {
            val EMPTY = State(
                catBreed = DataState.Loading,
                isFavorite = DataState.Loading
            )

            val PREVIEW = State(
                catBreed = DataState.Success(CatBreedUiModel.PREVIEW),
                isFavorite = DataState.Success(false)
            )
        }
    }
}