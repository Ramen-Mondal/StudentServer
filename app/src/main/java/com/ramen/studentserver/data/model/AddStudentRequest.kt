package com.ramen.studentserver.data.model

data class AddStudentRequest(
    val name: String,
    val studentClass: String,
    val roll: String,
    val email: String
)