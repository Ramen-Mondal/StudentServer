package com.ramen.studentserver.data.repository

import com.ramen.studentserver.data.model.AddStudentRequest
import com.ramen.studentserver.data.model.Student
import com.ramen.studentserver.data.remote.StudentApiService
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val api: StudentApiService
) {

    suspend fun getStudents(): List<Student> {
        return api.getStudents()
    }

    suspend fun getStudentById(id: Long): Student {
        return api.getStudentById(id)
    }

    suspend fun createStudent(student: AddStudentRequest): Student {
        return api.createStudent(student)
    }

    suspend fun updateStudent(id: Long, student: AddStudentRequest): Student {
        return api.updateStudent(id, student)
    }

    suspend fun updatePartialStudent(
        id: Long,
        update: Map<String, Any>
    ): Student {
        return api.updatePartialStudent(id, update)
    }

    suspend fun deleteStudent(id: Long) {
        api.deleteStudent(id)
    }
}