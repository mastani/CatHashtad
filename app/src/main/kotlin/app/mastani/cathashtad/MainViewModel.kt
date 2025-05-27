package app.mastani.cathashtad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mastani.cathashtad.domain.repository.app.AppConfigRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appConfigRepository: AppConfigRepository
) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow<Boolean?>(null)
    var isDarkTheme = _isDarkTheme.asStateFlow()

    init {
        fetchAppTheme()
    }

    private fun fetchAppTheme() {
        viewModelScope.launch {
            appConfigRepository.getIsDarkTheme().collectLatest {
                _isDarkTheme.emit(it)
            }
        }
    }

    fun setAppTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            appConfigRepository.setIsDarkTheme(isDarkTheme)
        }
    }
}