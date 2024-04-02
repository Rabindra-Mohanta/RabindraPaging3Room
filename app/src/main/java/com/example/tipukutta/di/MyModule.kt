package com.example.tipukutta.di

import android.content.Context
import androidx.room.Room
import com.example.tipukutta.BuildConfig
import com.example.tipukutta.constants.KeyConstants.DBNAME
import com.example.tipukutta.interfaces.Api
import com.example.tipukutta.roomDb.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MyModule {
    @Singleton
    @Provides
    fun getRetrofit():Api{
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
    }

    @Singleton
    @Provides
    fun getRoomDb(@ApplicationContext context: Context):RoomDb
    {
        return Room.databaseBuilder(context,RoomDb::class.java,DBNAME).build()
    }
}



