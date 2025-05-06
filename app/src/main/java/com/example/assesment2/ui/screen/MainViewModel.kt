package com.example.assesment2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assesment2.database.TaskHalderDao
import com.example.assesment2.model.TaskHalder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val dao: TaskHalderDao): ViewModel() {
    val halder : StateFlow<List<TaskHalder>> = dao.getTaskHalder().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun insert(title: String, categoty: String) {
        val taskHalder = TaskHalder(
            title = title,
            category = categoty
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertTaskHalder(taskHalder)
        }
    }

    fun getTaskHalder(id: Long): TaskHalder? {
        return null
    }
}