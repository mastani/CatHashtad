package app.mastani.cathashtad.domain.repository.app

import kotlinx.coroutines.flow.Flow

interface AppConfigRepository {
    suspend fun setIsDarkTheme(isDarkTheme: Boolean)
    fun getIsDarkTheme(): Flow<Boolean>
}