package com.yasin.mvvmwithcleanarchitecture.domain.use_case.get_all_post

import com.yasin.mvvmwithcleanarchitecture.common.Resource
import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto
import com.yasin.mvvmwithcleanarchitecture.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllPostUseCase @Inject constructor(
    private val repository:PostRepository
) {
    operator fun invoke(): Flow<Resource<List<PostDto>>> = flow {
        try {
            emit(Resource.Loading())
            val allPost = repository.getPosts()
            emit(Resource.Success(allPost))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Server error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Check internet connection."))
        }
    }

}