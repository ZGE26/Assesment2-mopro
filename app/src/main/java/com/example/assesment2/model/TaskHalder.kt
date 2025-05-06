package com.example.assesment2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskhalder")
data class TaskHalder(
    @PrimaryKey(autoGenerate = true)
    var id :Long = 0L,
    var title : String,
    var category : String,
)
