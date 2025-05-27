package app.mastani.cathashtad.data.datasource.local.database.favorite

import app.mastani.cathashtad.data.datasource.local.database.favorite.model.FavoriteEntity

interface FavoriteLocalDataSource {
    suspend fun getAll(): List<FavoriteEntity>
    suspend fun addToFavorite(favoriteId: String)
    suspend fun removeFromFavorite(favoriteId: String)
    suspend fun isFavorite(favoriteId: String): Boolean
}