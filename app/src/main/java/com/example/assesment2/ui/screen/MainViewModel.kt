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
}