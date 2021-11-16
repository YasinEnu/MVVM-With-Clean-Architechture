package com.yasin.mvvmwithcleanarchitecture.domain.repository

import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto

interface PostRepository {

    suspend fun getPosts(): List<PostDto>

    suspend fun getPostById(postId: String): PostDto

}