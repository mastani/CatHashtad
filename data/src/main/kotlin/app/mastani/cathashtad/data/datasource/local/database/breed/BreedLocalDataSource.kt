package app.mastani.cathashtad.data.datasource.local.database.breed

import androidx.paging.PagingSource
import app.mastani.cathashtad.data.datasource.local.database.breed.model.CatBreedEntity

interface BreedLocalDataSource {
    suspend fun getBreedById(id: String): CatBreedEntity?
    suspend fun getBreeds(ids: List<String>): List<CatBreedEntity>
    fun getPagingSource(): PagingSource<Int, CatBreedEntity>
    suspend fun insertAll(catBreeds: List<CatBreedEntity>)
    suspend fun deleteAll()
}