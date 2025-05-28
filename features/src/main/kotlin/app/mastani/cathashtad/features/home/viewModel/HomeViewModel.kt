package app.mastani.cathashtad.features.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import app.mastani.cathashtad.data.datasource.local.database.breed.model.CatBreedEntity
import app.mastani.cathashtad.data.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.retry
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    catBreedPager: Pager<Int, CatBreedEntity>
) : ViewModel(), HomeContract {

    val catBreedsFlow = catBreedPager.flow.map { pagingData ->
        pagingData.map { it.toUiModel() }
    }.cachedIn(viewModelScope)

    private val _state = MutableStateFlow(HomeContract.State.EMPTY)
    override val state: StateFlow<HomeContract.State>
        get() = _state.asStateFlow()

    private val effectChannel = Channel<HomeContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<HomeContract.Effect>
        get() = effectChannel.receiveAsFlow()

    override fun event(event: HomeContract.Event) {
    }
}