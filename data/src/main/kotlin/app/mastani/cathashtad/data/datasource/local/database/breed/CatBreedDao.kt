package app.mastani.cathashtad.data.datasource.local.database.breed

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.mastani.cathashtad.data.datasource.local.database.breed.model.CAT_BREEDS_TABLE_NAME
import app.mastani.cathashtad.data.datasource.local.database.breed.model.CatBreedEntity

@Dao
interface CatBreedDao {
    @Query("SELECT * FROM ${CAT_BREEDS_TABLE_NAME} WHERE id=:id")
    suspend fun getBreed(id: String): CatBreedEntity?

    @Query("SELECT * FROM ${CAT_BREEDS_TABLE_NAME} WHERE id IN (:ids)")
    suspend fun getBreeds(ids: List<String>): List<CatBreedEntity>

    @Query("SELECT * FROM ${CAT_BREEDS_TABLE_NAME}")
    fun getPagingSource(): PagingSource<Int, CatBreedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articleEntries: List<CatBreedEntity>)

    @Query("DELETE FROM ${CAT_BREEDS_TABLE_NAME}")
    suspend fun deleteAll()
}