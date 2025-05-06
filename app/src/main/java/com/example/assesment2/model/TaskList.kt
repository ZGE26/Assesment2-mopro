package com.example.assesment2.model

data class TaskList(
    val id: Long = 0L,
    val title: String,
    val description: String,
    val date: String,
    var halderId: Long,
)
