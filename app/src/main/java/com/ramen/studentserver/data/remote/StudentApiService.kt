package com.ramen.studentserver.data.remote

import com.ramen.studentserver.data.model.AddStudentRequest
import com.ramen.studentserver.data.model.Student
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudentApiService {

    @GET("students")
    suspend fun getStudents(): List<Student>

    @GET("students/{id}")
    suspend fun getStudentById(
        @Path("id") id: Long
    ): Student

    @POST("students")
    suspend fun createStudent(
        @Body student: AddStudentRequest
    ): Student

    @PUT("students/{id}")
    suspend fun updateStudent(
        @Path("id") id: Long,
        @Body student: AddStudentRequest
    ): Student

    @PATCH("students/{id}")
    suspend fun updatePartialStudent(
        @Path("id") id: Long,
        @Body update: Map<String, Any>
    ): Student

    @DELETE("students/{id}")
    suspend fun deleteStudent(
        @Path("id") id: Long
    )
}