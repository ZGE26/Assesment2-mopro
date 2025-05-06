package com.example.assesment2.ui.screen

import com.example.assesment2.model.TaskHalder
import com.example.assesment2.model.TaskList

class MainViewModel {
    val halder = listOf(
        TaskHalder(1,"Harian"),
        TaskHalder(2,"Mingguan"),
        TaskHalder(3,"Bulanan"),
        TaskHalder(4,"Tahunan"),
        TaskHalder(5,"Tugas"),
        TaskHalder(6,"Ujian"),
    )

    val listTaskList = listOf(
        TaskList(
            1,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            1
        ),
        TaskList(
            2,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            1
        ),
        TaskList(
            3,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            1
        ),
        TaskList(
            4,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            2
        ),
        TaskList(
            5,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            2
        ),
        TaskList(
            6,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            2
        ),
        TaskList(
            7,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            3
        ),
        TaskList(
            8,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            3
        ),
        TaskList(
            9,
            "Belajar",
            "Belajar Kotlin", "2023-10-01",
            3
        ),

    )
}