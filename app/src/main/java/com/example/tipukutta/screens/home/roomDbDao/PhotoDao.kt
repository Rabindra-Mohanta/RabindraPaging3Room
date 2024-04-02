package com.example.tipukutta.screens.home.roomDbDao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tipukutta.screens.home.model.PhotosData

@Dao
interface  PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhoto(data:List<PhotosData>)
    @Query("SELECT * FROM PhotosData")
     fun getAllPhoto():PagingSource<Int,PhotosData>
    @Query("DELETE FROM PhotosData")
    suspend fun deletePhotos()

}
