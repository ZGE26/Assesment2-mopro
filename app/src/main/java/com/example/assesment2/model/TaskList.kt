package com.example.assesment2.model

data class TaskList(
    val id: Long = 0L,
    var title: String,
    var description: String,
    var date: String,
    var halderId: Long,
)
