package com.example.composetest.pathway.statecodelab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WellnessTask(
    val id: Int,
    val label: String,
    initialCheck: Boolean = false
) {
    var isChecked by mutableStateOf(initialCheck)
}

data class WellnessTaskItemModel(
    val id: Int,
    val name: String
)