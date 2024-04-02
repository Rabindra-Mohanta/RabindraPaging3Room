package com.example.tipukutta.screens.home.remoteMediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.tipukutta.interfaces.Api
import com.example.tipukutta.roomDb.RoomDb
import com.example.tipukutta.screens.home.model.PhotoRemoteKeys
import com.example.tipukutta.screens.home.model.PhotosData

@OptIn(ExperimentalPagingApi::class)
class HomeRemoteMediator(private val api: Api, private val myRoomDb: RoomDb) :
    RemoteMediator<Int, PhotosData>() {
    val photoDao = myRoomDb.getPhotoDao()
    val photoRemoteKeyDao = myRoomDb.getPhotoRemoteKeysDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotosData>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyFromLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }


            }
            val res = api.getPhotos(page = currentPage)

            val endOfPagination = false
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPagination) null else currentPage + 1
            myRoomDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    photoDao.deletePhotos()
                    photoRemoteKeyDao.deleteAll()
                }
                photoDao.insertAllPhoto(res.body()!!)
                val keys = res.body()!!.map { data ->
                    PhotoRemoteKeys(id = data.id, prevPage = prevPage, nextPage = nextPage)
                }
                photoRemoteKeyDao.addData(keys)
            }
            MediatorResult.Success(endOfPagination)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyFromLastItem(state: PagingState<Int, PhotosData>): PhotoRemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            photoRemoteKeyDao.getKeys(id = data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PhotosData>): PhotoRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                photoRemoteKeyDao.getKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PhotosData>): PhotoRemoteKeys? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { data ->
                photoRemoteKeyDao.getKeys(id = data.id)

            }
    }


}

