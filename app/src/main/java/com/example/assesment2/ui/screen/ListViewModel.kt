package com.example.assesment2.ui.screen

import androidx.lifecycle.ViewModel
import com.example.assesment2.model.TaskList

class ListViewModel: ViewModel() {
    val listTaskList = listOf(
        TaskList(
            1,
            "Belajar 1",
            "Belajar Kotlin", "2023-10-01",
            1
        ),
        TaskList(
            2,
            "Belajar 2",
            "Belajar Kotlin", "2023-10-01",
            1
        ),
        TaskList(
            3,
            "Belajar 3",
            "Belajar Kotlin", "2023-10-01",
            1
        ),
        TaskList(
            4,
            "Belajar  4",
            "Belajar Kotlin", "2023-10-01",
            2
        ),
        TaskList(
            5,
            "Belajar 5",
            "Belajar Kotlin", "2023-10-01",
            2
        ),
        TaskList(
            6,
            "Belajar 6",
            "Belajar Kotlin", "2023-10-01",
            2
        ),
        TaskList(
            7,
            "Belajar 7",
            "Belajar Kotlin", "2023-10-01",
            3
        ),
        TaskList(
            8,
            "Belajar 8",
            "Belajar Kotlin", "2023-10-01",
            3
        ),
        TaskList(
            9,
            "Belajar 9",
            "Belajar Kotlin", "2023-10-01",
            3
        ),

        )
}