package app.mastani.cathashtad.data.datasource.local.database.favorite.di

import app.mastani.cathashtad.data.datasource.local.database.favorite.FavoriteLocalDataSource
import app.mastani.cathashtad.data.datasource.local.database.favorite.FavoriteLocalDataSourceImpl
import app.mastani.cathashtad.data.repository.favorite.FavoriteRepositoryImpl
import app.mastani.cathashtad.domain.repository.favorite.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteBinderModule {
    @Binds
    abstract fun bindFavoriteLocalDataSource(favoriteLocalDataSourceImpl: FavoriteLocalDataSourceImpl): FavoriteLocalDataSource

    @Binds
    abstract fun bindFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}