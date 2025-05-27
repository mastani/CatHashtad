package app.mastani.cathashtad.data.datasource.remote.breed.di

import app.mastani.cathashtad.data.datasource.remote.breed.BreedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object BreedModule {
    @Provides
    fun provideBreedApi(retrofit: Retrofit) = retrofit.create(BreedApi::class.java)
}