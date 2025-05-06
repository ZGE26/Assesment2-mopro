package com.example.assesment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.assesment2.model.TaskHalder
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskHalderDao {
    @Insert
    suspend fun insertTaskHalder(taskHalder: TaskHalder): Long

    @Update
    suspend fun updateTaskHalder(taskHalder: TaskHalder)

    @Query("SELECT * FROM taskhalder ORDER BY id DESC")
    fun getTaskHalder(): Flow<List<TaskHalder>>
}