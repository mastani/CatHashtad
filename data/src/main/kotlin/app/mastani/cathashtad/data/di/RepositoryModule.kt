package app.mastani.cathashtad.data.di

import app.mastani.cathashtad.data.repository.app.AppConfigRepositoryImpl
import app.mastani.cathashtad.domain.repository.app.AppConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppConfigRepository(appConfigRepositoryImpl: AppConfigRepositoryImpl): AppConfigRepository
}