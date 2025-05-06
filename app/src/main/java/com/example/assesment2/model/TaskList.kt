package com.example.assesment2.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "tasklist",
    foreignKeys = [
        ForeignKey(
            entity = TaskHalder::class,
            parentColumns = ["id"],
            childColumns = ["halderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TaskList(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var title: String,
    var description: String,
    var date: String,

    @ColumnInfo(index = true)
    var halderId: Long
)
