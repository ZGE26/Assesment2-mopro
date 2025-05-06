package com.example.assesment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.assesment2.model.TaskList
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskListDao {
    // Insert a new task
    @Insert
    suspend fun insertTask(task: TaskList)

    // Update an existing task
    @Update
    suspend fun updateTask(task: TaskList)

    @Query("SELECT * FROM tasklist ORDER BY date DESC")
    fun getList(): Flow<List<TaskList>>
}