package app.mastani.cathashtad.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import app.mastani.cathashtad.data.datasource.local.database.favorite.FavoriteDao
import app.mastani.cathashtad.data.datasource.local.database.favorite.model.FavoriteEntity
import app.mastani.cathashtad.data.datasource.local.database.breed.CatBreedDao
import app.mastani.cathashtad.data.datasource.local.database.breed.model.CatBreedEntity
import app.mastani.cathashtad.data.datasource.local.database.remotekeys.BreedRemoteKeyEntity
import app.mastani.cathashtad.data.datasource.local.database.remotekeys.BreedRemoteKeysDao
import java.util.Date

@Database(entities = [CatBreedEntity::class, FavoriteEntity::class, BreedRemoteKeyEntity::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedsDao(): CatBreedDao
    abstract fun breedRemoteKeysDao() : BreedRemoteKeysDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val DB_NAME = "cats.db"
    }
}

object DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}