package com.example.tipukutta.screens.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotosData(val albumId:Int,@PrimaryKey(autoGenerate = false) val id:Int,val title:String,val url:String,val  thumbnailUrl:String)

