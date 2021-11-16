package com.yasin.mvvmwithcleanarchitecture.data.remote.dto

data class PostDto(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)