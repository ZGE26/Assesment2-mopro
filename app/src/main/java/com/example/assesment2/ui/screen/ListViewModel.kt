package com.example.assesment2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assesment2.database.TaskListDao
import com.example.assesment2.model.TaskList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(private val dao: TaskListDao) : ViewModel() {
    val listTaskList: StateFlow<List<TaskList>> = dao.getList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun insert(title: String, description: String, date: String, halderId: Long) {
        val taskList = TaskList(
            title = title,
            description = description,
            date = date,
            halderId = halderId
        )
        viewModelScope.launch {
            dao.insertTask(taskList)
        }
    }

    fun update(taskList: TaskList) {
        viewModelScope.launch {
            dao.updateTask(taskList)
        }
    }
}
