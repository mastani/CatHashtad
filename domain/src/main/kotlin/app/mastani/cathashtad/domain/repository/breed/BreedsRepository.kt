package app.mastani.cathashtad.domain.repository.breed

import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel

interface BreedsRepository {
    suspend fun fetchCatBreeds(page: Int, limit: Int): Result<List<CatBreedUiModel>>
    suspend fun fetchCatBreedDetail(id: String): Result<CatBreedUiModel>
    suspend fun searchCatBreeds(query: String): Result<List<CatBreedUiModel>>
}