package app.mastani.cathashtad.data.datasource.local.database.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.mastani.cathashtad.data.datasource.local.database.favorite.model.FAVORITES_TABLE_NAME
import app.mastani.cathashtad.data.datasource.local.database.favorite.model.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM ${FAVORITES_TABLE_NAME}")
    suspend fun getAll(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM ${FAVORITES_TABLE_NAME} WHERE id = :id")
    suspend fun delete(id : String)

    @Query("SELECT EXISTS(SELECT * FROM ${FAVORITES_TABLE_NAME} WHERE id = :id)")
    suspend fun isFavorite(id : String) : Boolean
}