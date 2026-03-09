package com.ramen.studentserver.ui.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramen.studentserver.data.model.AddStudentRequest
import com.ramen.studentserver.data.model.Student
import com.ramen.studentserver.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _allStudents = MutableStateFlow<List<Student>>(emptyList())

    init {
        fetchStudents()
    }

    fun fetchStudents() {
        viewModelScope.launch {
            val result = repository.getStudents()
            _allStudents.value = result
            filterStudents()
        }
    }

    fun onSearchChange(query: String) {
        _searchQuery.value = query
        filterStudents()
    }

    private fun filterStudents() {
        val query = _searchQuery.value.lowercase()

        _students.value = if (query.isEmpty()) {
            _allStudents.value
        } else {
            _allStudents.value.filter {
                it.name.lowercase().contains(query) ||
                        it.email.lowercase().contains(query)
            }
        }
    }

    fun deleteStudent(id: Long) {
        viewModelScope.launch {
            repository.deleteStudent(id)
            fetchStudents()
        }
    }

    fun addStudent(name: String,studentClass: String, roll: String, email: String) {
        viewModelScope.launch {
            repository.createStudent(AddStudentRequest(name, studentClass, roll, email))
            fetchStudents()
        }
    }
}