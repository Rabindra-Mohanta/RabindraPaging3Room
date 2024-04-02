package com.example.tipukutta.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tipukutta.screens.home.model.PhotoRemoteKeys
import com.example.tipukutta.screens.home.model.PhotosData
import com.example.tipukutta.screens.home.roomDbDao.PhotoDao
import com.example.tipukutta.screens.home.roomDbDao.PhotoRemoteKeyDao

@Database(entities = [PhotoRemoteKeys::class, PhotosData::class], version = 1)
abstract class RoomDb() : RoomDatabase() {
    abstract fun getPhotoDao(): PhotoDao
    abstract fun getPhotoRemoteKeysDao(): PhotoRemoteKeyDao
}
