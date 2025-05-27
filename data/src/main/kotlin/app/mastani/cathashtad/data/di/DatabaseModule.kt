package app.mastani.cathashtad.data.di

import android.app.Application
import androidx.room.Room
import app.mastani.cathashtad.data.datasource.local.database.AppDatabase
import app.mastani.cathashtad.data.datasource.local.database.favorite.FavoriteDao
import app.mastani.cathashtad.data.datasource.local.database.breed.CatBreedDao
import app.mastani.cathashtad.data.datasource.local.database.remotekeys.BreedRemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideBreedsDao(database: AppDatabase): CatBreedDao = database.breedsDao()

    @Provides
    fun provideFavoriteDao(database: AppDatabase): FavoriteDao = database.favoriteDao()

    @Provides
    fun provideBreedRemoteKeysDao(database: AppDatabase): BreedRemoteKeysDao = database.breedRemoteKeysDao()
}