package com.yasin.mvvmwithcleanarchitecture.presentation.post_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasin.mvvmwithcleanarchitecture.common.Resource
import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto
import com.yasin.mvvmwithcleanarchitecture.domain.use_case.get_post.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase
): ViewModel()  {

    val progressBarLiveData = MutableLiveData<Boolean>()
    val getPostSuccessfulData = MutableLiveData<PostDto>()
    val getPostFailedData = MutableLiveData<String>()

    fun getSinglePosts(postId:String) {
        getPostUseCase(postId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getPostSuccessfulData.postValue(result.data)
                    progressBarLiveData.postValue(false)
                }
                is Resource.Error -> {
                    getPostFailedData.postValue(result.message ?: "Unexpected error occurred")
                    progressBarLiveData.postValue(false)
                }
                is Resource.Loading -> {
                    progressBarLiveData.postValue(true)
                }
            }
        }.launchIn(viewModelScope)
    }

}