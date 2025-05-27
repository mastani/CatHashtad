package app.mastani.cathashtad.data.datasource.remote.breed.di

import app.mastani.cathashtad.data.datasource.remote.breed.BreedRemoteDataSource
import app.mastani.cathashtad.data.datasource.remote.breed.BreedRemoteDataSourceImpl
import app.mastani.cathashtad.data.repository.breed.BreedsRepositoryImpl
import app.mastani.cathashtad.domain.repository.breed.BreedsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BreedBinderModule {
    @Binds
    abstract fun bindBreedRemoteDataSource(breedRemoteDataSourceImpl: BreedRemoteDataSourceImpl): BreedRemoteDataSource

    @Binds
    abstract fun bindBreedRepository(breedsRepositoryImpl: BreedsRepositoryImpl): BreedsRepository
}