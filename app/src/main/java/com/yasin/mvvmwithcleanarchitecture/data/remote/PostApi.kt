package com.yasin.mvvmwithcleanarchitecture.data.remote

import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("posts/{postId}")
    suspend fun getPostsById(@Path("postId") postId: String): PostDto

}