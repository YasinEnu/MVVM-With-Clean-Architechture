package com.yasin.mvvmwithcleanarchitecture.presentation.post_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasin.mvvmwithcleanarchitecture.common.Resource
import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto
import com.yasin.mvvmwithcleanarchitecture.domain.use_case.get_all_post.GetAllPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllPostUseCase: GetAllPostUseCase
):ViewModel() {

    val progressBarLiveData = MutableLiveData<Boolean>()
    val getAllPostSuccessfulData = MutableLiveData<List<PostDto>>()
    val getAllPostFailedData = MutableLiveData<String>()


    init {
        getPosts()
    }

    private fun getPosts() {
        getAllPostUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getAllPostSuccessfulData.postValue(result.data ?: emptyList())
                    progressBarLiveData.postValue(false)
                }
                is Resource.Error -> {
                    getAllPostFailedData.postValue(result.message ?: "Unexpected error occurred")
                    progressBarLiveData.postValue(false)
                }
                is Resource.Loading -> {
                    progressBarLiveData.postValue(true)
                }
            }
        }.launchIn(viewModelScope)
    }
}