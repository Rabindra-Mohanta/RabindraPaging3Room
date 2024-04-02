package com.example.tipukutta.screens.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoRemoteKeys(@PrimaryKey(autoGenerate = false) val id:Int,val prevPage:Int?,val nextPage:Int?)

