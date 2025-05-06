package com.example.assesment2.navigation

import com.example.assesment2.ui.screen.KEY_ID_TASK

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object ListTask: Screen("listTask/{$KEY_ID_TASK}") {
        fun passHalderId(taskId: Long) = "listTask/$taskId"
    }
}