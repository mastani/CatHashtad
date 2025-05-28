package app.mastani.cathashtad.data.repository.breed

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import app.mastani.cathashtad.data.datasource.local.database.AppDatabase
import app.mastani.cathashtad.data.datasource.local.database.breed.model.CatBreedEntity
import app.mastani.cathashtad.data.datasource.local.database.remotekeys.BreedRemoteKeyEntity
import app.mastani.cathashtad.data.mapper.toEntity
import app.mastani.cathashtad.domain.repository.breed.BreedsRepository

@OptIn(ExperimentalPagingApi::class)
class CatBreedPagingMediator(
    private val breedsRepository: BreedsRepository,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, CatBreedEntity>() {

    private val breeds = appDatabase.breedsDao()
    private val breedRemoteKeysDao = appDatabase.breedRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatBreedEntity>
    ): MediatorResult {

        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 0
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = breedsRepository.fetchCatBreeds(
                page = currentPage,
                limit = state.config.pageSize
            )

            val data = response.getOrThrow() // This will throw if network request failed
            val endOfPaginationReached = data.isEmpty()

            val prevPage = if (currentPage == 0) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    breeds.deleteAll()
                    breedRemoteKeysDao.deleteAllRemoteKeys()
                }

                val entries = response.getOrNull()?.map { it.toEntity() }
                entries?.let {
                    breeds.insertAll(it)
                }

                val keys = response.getOrNull()?.map { breed ->
                    BreedRemoteKeyEntity(
                        id = breed.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                keys?.let {
                    breedRemoteKeysDao.addAllRemoteKeys(remoteKeys = it)
                }
            }
            MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, CatBreedEntity>
    ): BreedRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                breedRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, CatBreedEntity>
    ): BreedRemoteKeyEntity? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { breed ->
            breedRemoteKeysDao.getRemoteKeys(breed.id)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, CatBreedEntity>
    ): BreedRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { breed ->
            breedRemoteKeysDao.getRemoteKeys(breed.id)
        }
    }
}