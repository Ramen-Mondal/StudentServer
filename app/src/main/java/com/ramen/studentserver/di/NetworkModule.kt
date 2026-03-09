package com.ramen.studentserver.di

import com.ramen.studentserver.data.remote.StudentApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    private const val BASE_URL = "https://spring-boot-deployment-revision-4.onrender.com"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideStudentApi(retrofit: Retrofit): StudentApiService {
        return retrofit.create(StudentApiService::class.java)
    }


}