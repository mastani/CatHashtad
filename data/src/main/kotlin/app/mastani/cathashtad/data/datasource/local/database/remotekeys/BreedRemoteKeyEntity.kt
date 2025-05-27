package app.mastani.cathashtad.data.datasource.local.database.remotekeys

import androidx.room.Entity
import androidx.room.PrimaryKey

const val BREED_REMOTE_KEYS_TABLE_NAME = "breed_remote_keys"

@Entity(tableName = BREED_REMOTE_KEYS_TABLE_NAME)
data class BreedRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val prevPage : Int?,
    val nextPage : Int?
)