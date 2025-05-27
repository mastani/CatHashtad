package app.mastani.cathashtad.data.datasource.local.database.favorite.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val FAVORITES_TABLE_NAME = "favorites"

@Entity(tableName = FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    val id: String
)