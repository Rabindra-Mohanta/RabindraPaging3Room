package com.example.tipukutta.screens.home.roomDbDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tipukutta.screens.home.model.PhotoRemoteKeys

@Dao
interface PhotoRemoteKeyDao {
    @Query("SELECT * FROM PhotoRemoteKeys WHERE id=:id")
    suspend fun getKeys(id:Int?):PhotoRemoteKeys
    @Query("DELETE FROM PhotoRemoteKeys")
    suspend fun deleteAll()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data:List<PhotoRemoteKeys>)
}
