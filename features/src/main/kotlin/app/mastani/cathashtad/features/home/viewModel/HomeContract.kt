package app.mastani.cathashtad.features.home.viewModel

import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.common.DataState
import app.mastani.cathashtad.features.common.UnidirectionalViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface HomeContract : UnidirectionalViewModel<HomeContract.Event, HomeContract.Effect, HomeContract.State> {

    sealed interface Event

    sealed interface Effect {
        data class OnDisplayMessage(val message: String?) : Effect
    }

    data class State(
        val catBreeds: DataState<ImmutableList<CatBreedUiModel>>
    ) {
        companion object {
            val EMPTY = State(
                catBreeds = DataState.Loading,
            )

            val PREVIEW = State(
                catBreeds = DataState.Success(
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