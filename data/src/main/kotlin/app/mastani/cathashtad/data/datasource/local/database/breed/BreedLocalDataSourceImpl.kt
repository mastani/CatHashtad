package app.mastani.cathashtad.data.datasource.local.database.breed

import app.mastani.cathashtad.data.datasource.local.database.breed.model.CatBreedEntity
import javax.inject.Inject

class BreedLocalDataSourceImpl @Inject constructor(
    private val catBreedDao: CatBreedDao
): BreedLocalDataSource {

    override suspend fun getBreedById(id: String) = catBreedDao.getBreed(id)

    override suspend fun getBreeds(ids: List<String>) = catBreedDao.getBreeds(ids)

    override fun getPagingSource() = catBreedDao.getPagingSource()

    override suspend fun insertAll(catBreeds: List<CatBreedEntity>) {
        catBreedDao.insertAll(catBreeds)
    }

    override suspend fun deleteAll() {
        catBreedDao.deleteAll()
    }
}