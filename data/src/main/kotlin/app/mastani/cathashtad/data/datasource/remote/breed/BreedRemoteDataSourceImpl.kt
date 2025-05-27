package app.mastani.cathashtad.data.datasource.remote.breed

import app.mastani.cathashtad.data.datasource.remote.breed.model.CatBreedDto
import app.mastani.cathashtad.data.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BreedRemoteDataSourceImpl @Inject constructor(
    private val breedApi: BreedApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BreedRemoteDataSource {

    override suspend fun fetchCatBreeds(page: Int, limit: Int): Result<List<CatBreedDto>> {
        return withContext(ioDispatcher) {
            runCatching { breedApi.getCatBreeds(page, limit) }
        }
    }

    override suspend fun fetchCatBreedDetail(id: String): Result<CatBreedDto> {
        return withContext(ioDispatcher) {
            runCatching { breedApi.getCatBreed(id) }
        }
    }

    override suspend fun searchCatBreeds(query: String): Result<List<CatBreedDto>> {
        return withContext(ioDispatcher) {
            runCatching { breedApi.searchCatBreeds(query) }
        }
    }
}