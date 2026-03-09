package com.ramen.studentserver.ui.student

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ramen.studentserver.data.model.Student

@Composable
fun StudentScreen(
    modifier: Modifier = Modifier,
    viewModel: StudentViewModel = hiltViewModel(),
    onStudentClick: (String) -> Unit,
    onAddStudentClick: () -> Unit
) {

    val students by viewModel.students.collectAsState()
    val search by viewModel.searchQuery.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddStudentClick
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Student")
            }
        }
    ) { padding ->

        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = search,
                onValueChange = { viewModel.onSearchChange(it) },
                label = { Text("Search Student") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            LazyColumn {
                items(
                    items = students,
                    key = { it.id } // IMPORTANT for performance
                ) { student ->

                    StudentItem(
                        student = student,
                        onClick = { onStudentClick(student.id.toString()) },
                        onLongPress = {
                            viewModel.deleteStudent(student.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StudentItem(
    student: Student,
    onClick: () -> Unit,
    onLongPress: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongPress
            ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(text = student.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Class: ${student.studentClass}")
            }

            Text(text = "Roll: ${student.roll}")
        }
    }
}