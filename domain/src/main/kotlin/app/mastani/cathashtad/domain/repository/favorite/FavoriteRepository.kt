package app.mastani.cathashtad.domain.repository.favorite

import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel

interface FavoriteRepository {
    suspend fun getAll(): List<CatBreedUiModel>
    suspend fun addToFavorite(favoriteId: String)
    suspend fun removeFromFavorite(favoriteId: String)
    suspend fun isFavorite(favoriteId: String): Boolean
}