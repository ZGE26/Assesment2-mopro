package com.example.assesment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assesment2.model.TaskHalder
import com.example.assesment2.model.TaskList
import com.example.assesment2.database.TaskHalderDao
import com.example.assesment2.database.TaskListDao

@Database(entities = [TaskHalder::class, TaskList::class], version = 2, exportSchema = false)
abstract class TaskDb : RoomDatabase() {

    abstract fun taskHalderDao(): TaskHalderDao
    abstract fun taskListDao(): TaskListDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDb? = null

        fun getDatabase(context: Context): TaskDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDb::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
