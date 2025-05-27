package app.mastani.cathashtad.data.datasource.local.database.remotekeys

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedRemoteKeysDao {
    @Query("SELECT * FROM $BREED_REMOTE_KEYS_TABLE_NAME where id=:id")
    suspend fun getRemoteKeys(id: String): BreedRemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<BreedRemoteKeyEntity>)

    @Query("DELETE FROM $BREED_REMOTE_KEYS_TABLE_NAME")
    suspend fun deleteAllRemoteKeys()
}