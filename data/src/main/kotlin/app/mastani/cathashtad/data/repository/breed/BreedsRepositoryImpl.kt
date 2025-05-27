package app.mastani.cathashtad.data.repository.breed

import app.mastani.cathashtad.data.datasource.local.database.breed.BreedLocalDataSource
import app.mastani.cathashtad.data.datasource.remote.breed.BreedRemoteDataSource
import app.mastani.cathashtad.data.mapper.toUiModel
import app.mastani.cathashtad.domain.repository.breed.BreedsRepository
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val breedRemoteDataSource: BreedRemoteDataSource,
    private val breedLocalDataSource: BreedLocalDataSource
) : BreedsRepository {

    override suspend fun fetchCatBreeds(page: Int, limit: Int): Result<List<CatBreedUiModel>> {
        return breedRemoteDataSource.fetchCatBreeds(page, limit)
            .map { list -> list.map { it.toUiModel() } }
    }

    override suspend fun fetchCatBreedDetail(id: String): Result<CatBreedUiModel> {
        val breed = breedLocalDataSource.getBreedById(id)
        return if (breed != null) {
            Result.success(breed.toUiModel())
        } else {
            breedRemoteDataSource.fetchCatBreedDetail(id).map { it.toUiModel() }
        }
    }

    override suspend fun searchCatBreeds(query: String): Result<List<CatBreedUiModel>> {
        return breedRemoteDataSource.searchCatBreeds(query).map { list -> list.map { it.toUiModel() } }
    }
}