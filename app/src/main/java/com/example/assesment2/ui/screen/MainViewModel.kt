package com.example.assesment2.ui.screen

import com.example.assesment2.model.TaskHalder
import androidx.lifecycle.ViewModel
import com.example.assesment2.model.TaskList

class MainViewModel: ViewModel() {
    val halder = listOf(
        TaskHalder(1,"Harian", "Tugas Harian"),
        TaskHalder(2,"Mingguan", "Tugas Mingguan"),
        TaskHalder(3,"Bulanan", "Tugas Bulanan"),
        TaskHalder(4,"Tahunan", "Tugas Tahunan"),
        TaskHalder(5,"Tugas", "Tugas"),
        TaskHalder(6,"Ujian", "Ujian"),
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