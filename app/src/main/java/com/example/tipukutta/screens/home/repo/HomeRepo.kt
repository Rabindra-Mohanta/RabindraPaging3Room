package com.example.tipukutta.screens.home.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.tipukutta.interfaces.Api
import com.example.tipukutta.roomDb.RoomDb
import com.example.tipukutta.screens.home.remoteMediator.HomeRemoteMediator
import javax.inject.Inject

class HomeRepo @Inject constructor(private val api: Api, private val myRoomDb: RoomDb) {
    @OptIn(ExperimentalPagingApi::class)
    fun getAllPhoto() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 30),
        remoteMediator = HomeRemoteMediator(api, myRoomDb),
        pagingSourceFactory = { myRoomDb.getPhotoDao().getAllPhoto() }).flow

}

