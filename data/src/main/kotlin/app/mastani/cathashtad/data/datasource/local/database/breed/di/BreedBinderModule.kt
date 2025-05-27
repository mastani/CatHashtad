package app.mastani.cathashtad.data.datasource.local.database.breed.di

import app.mastani.cathashtad.data.datasource.local.database.breed.BreedLocalDataSource
import app.mastani.cathashtad.data.datasource.local.database.breed.BreedLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BreedBinderModule {
    @Binds
    abstract fun bindBreedLocalDataSource(breedLocalDataSourceImpl: BreedLocalDataSourceImpl): BreedLocalDataSource
}