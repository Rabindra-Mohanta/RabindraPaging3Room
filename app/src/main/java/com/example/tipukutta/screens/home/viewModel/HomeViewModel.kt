package com.example.tipukutta.screens.home.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.tipukutta.screens.home.model.PhotosData
import com.example.tipukutta.screens.home.repo.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo,
    @ApplicationContext val context: Context
) : ViewModel() {
    private val _photoData = MutableLiveData<PagingData<PhotosData>>()
    val photoData
        get() = _photoData

    init {
        getPhotoData()
    }

    private fun getPhotoData() {
        viewModelScope.launch {
            val data = homeRepo.getAllPhoto()
            data.catch {
                it.printStackTrace()
            }.collect {
                _photoData.value = it
            }
        }
    }

}

