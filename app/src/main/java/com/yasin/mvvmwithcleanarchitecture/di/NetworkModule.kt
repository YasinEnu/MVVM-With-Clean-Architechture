package com.yasin.mvvmwithcleanarchitecture.di

import com.yasin.mvvmwithcleanarchitecture.common.Constants
import com.yasin.mvvmwithcleanarchitecture.data.remote.PostApi
import com.yasin.mvvmwithcleanarchitecture.data.repository.PostRepositoryImpl
import com.yasin.mvvmwithcleanarchitecture.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun providePostApi(): PostApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: PostApi): PostRepository {
        return PostRepositoryImpl(api)
    }

}