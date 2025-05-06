package com.example.assesment2.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assesment2.database.TaskDb
import com.example.assesment2.ui.screen.ListViewModel
import com.example.assesment2.ui.screen.MainViewModel

class ViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = TaskDb.getDatabase(context)
        val taskHalderDao = db.taskHalderDao()
        val taskListDao = db.taskListDao()

        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(taskHalderDao) as T
        }

        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(taskListDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}