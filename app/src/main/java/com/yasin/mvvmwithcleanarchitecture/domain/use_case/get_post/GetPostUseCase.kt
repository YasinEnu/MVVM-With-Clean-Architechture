package com.yasin.mvvmwithcleanarchitecture.domain.use_case.get_post

import com.yasin.mvvmwithcleanarchitecture.common.Resource
import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto
import com.yasin.mvvmwithcleanarchitecture.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository:PostRepository
) {
    operator fun invoke(postId:String): Flow<Resource<PostDto>> = flow {
        try {
            emit(Resource.Loading())
            val post = repository.getPostById(postId)
            emit(Resource.Success(post))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Server error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Check internet connection."))
        }
    }

}