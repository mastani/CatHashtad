package app.mastani.cathashtad.data.repository.breed.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.mastani.cathashtad.data.datasource.local.database.AppDatabase
import app.mastani.cathashtad.data.datasource.local.database.breed.model.CatBreedEntity
import app.mastani.cathashtad.data.repository.breed.CatBreedPagingMediator
import app.mastani.cathashtad.domain.repository.breed.BreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BreedPagerModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideCatBreedPager(
        appDatabase: AppDatabase,
        breedsRepository: BreedsRepository
    ): Pager<Int, CatBreedEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 20),
            remoteMediator = CatBreedPagingMediator(
                breedsRepository = breedsRepository,
                appDatabase = appDatabase,
            ),
            pagingSourceFactory = {
                appDatabase.breedsDao().getPagingSource()
            }
        )
    }
}