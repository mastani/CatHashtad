package app.mastani.cathashtad.data.datasource.remote.breed

import app.mastani.cathashtad.data.datasource.remote.breed.model.CatBreedDto

interface BreedRemoteDataSource {
    suspend fun fetchCatBreeds(page: Int, limit: Int): Result<List<CatBreedDto>>
    suspend fun fetchCatBreedDetail(id: String): Result<CatBreedDto>
    suspend fun searchCatBreeds(query: String): Result<List<CatBreedDto>>
}