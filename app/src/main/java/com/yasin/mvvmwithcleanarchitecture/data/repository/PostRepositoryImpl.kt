package com.yasin.mvvmwithcleanarchitecture.data.repository

import com.yasin.mvvmwithcleanarchitecture.data.remote.PostApi
import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto
import com.yasin.mvvmwithcleanarchitecture.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostApi
):PostRepository {
    override suspend fun getPosts(): List<PostDto> {
        return api.getPosts()
    }

    override suspend fun getPostById(postId: String): PostDto {
        return api.getPostsById(postId)
    }
}