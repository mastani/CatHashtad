package app.mastani.cathashtad.data.datasource.remote.breed

import app.mastani.cathashtad.data.datasource.remote.breed.model.CatBreedDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreedApi {

    @GET("v1/breeds")
    suspend fun getCatBreeds(
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 10
    ): List<CatBreedDto>

    @GET("v1/breeds/{id}")
    suspend fun getCatBreed(
        @Path("id") id: String
    ): CatBreedDto

    @GET("v1/breeds/search")
    suspend fun searchCatBreeds(
        @Query("q") query: String,
        @Query("attach_image") attachImage: Boolean = true
    ): List<CatBreedDto>
}