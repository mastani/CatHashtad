package app.mastani.cathashtad.features.common

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val errorMessage: String) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}
