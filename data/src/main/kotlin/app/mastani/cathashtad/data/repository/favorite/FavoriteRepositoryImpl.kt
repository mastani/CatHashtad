package app.mastani.cathashtad.data.repository.favorite

import app.mastani.cathashtad.data.datasource.local.database.breed.BreedLocalDataSource
import app.mastani.cathashtad.data.datasource.local.database.favorite.FavoriteDao
import app.mastani.cathashtad.data.datasource.local.database.favorite.model.FavoriteEntity
import app.mastani.cathashtad.data.mapper.toUiModel
import app.mastani.cathashtad.domain.repository.breed.BreedsRepository
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val breedLocalDataSource: BreedLocalDataSource
) : FavoriteRepository {

    override suspend fun getAll(): List<CatBreedUiModel> {
        val favoritesIds = favoriteDao.getAll().map { it.id }
        return breedLocalDataSource.getBreeds(favoritesIds).map { it.toUiModel() }
    }

    override suspend fun addToFavorite(favoriteId: String) = favoriteDao.insert(FavoriteEntity(favoriteId))

    override suspend fun removeFromFavorite(favoriteId: String) = favoriteDao.delete(favoriteId)

    override suspend fun isFavorite(favoriteId: String) = favoriteDao.isFavorite(favoriteId)
}