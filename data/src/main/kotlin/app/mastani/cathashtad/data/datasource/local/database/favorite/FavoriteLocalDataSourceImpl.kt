package app.mastani.cathashtad.data.datasource.local.database.favorite

import app.mastani.cathashtad.data.datasource.local.database.favorite.model.FavoriteEntity
import javax.inject.Inject

class FavoriteLocalDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
): FavoriteLocalDataSource {

    override suspend fun getAll() = favoriteDao.getAll()

    override suspend fun addToFavorite(favoriteId: String) {
        favoriteDao.insert(FavoriteEntity(favoriteId))
    }

    override suspend fun removeFromFavorite(favoriteId: String) {
        favoriteDao.delete(favoriteId)
    }

    override suspend fun isFavorite(favoriteId: String): Boolean {
        return favoriteDao.isFavorite(favoriteId)
    }
}