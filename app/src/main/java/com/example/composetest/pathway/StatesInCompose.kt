package com.example.composetest.pathway

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetest.WellnessTask
import com.example.composetest.ui.theme.Blue
import com.example.composetest.ui.theme.LightBlue

@Composable
fun StatefulWellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = viewModel()
) {

    Column(modifier = modifier) {
//        StatefulCounter()

        WellnessTaskItemList(list = viewModel.tasks, onClose = {
            viewModel.remove(it)
        }, checkChange = { task, value ->
            viewModel.changeTaskChecked(task.id, value)
        })
    }
}

@Composable
fun StatefulWellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
    checkChange: (Boolean) -> Unit,
    checked: Boolean
) {

    WellnessTaskItem(
        taskName = taskName,
        onClose = onClose,
        onCheckedChange = {
            checkChange(it)
        },
        checked = checked
    )
}
//
//@Composable
//fun StatefulCounter(modifier: Modifier = Modifier) {
//
//    var count by rememberSaveable { mutableStateOf(0) }
//    StatelessCounter(count = count, onclick = { count++ }, modifier = modifier)
//}
//
//@Composable
//fun StatelessCounter(
//    count: Int,
//    onclick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//
//    Column(modifier = modifier.padding(all = 16.dp)) {
//        Text(text = "You've had $count glasses")
//
//        Button(
//            onClick = { onclick() },
//            modifier = Modifier.padding(top = 8.dp),
//            enabled = count < 10
//        ) {
//            Text(text = "Add one")
//        }
//    }
//}

@Composable
fun WellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        color = Blue,
        modifier = Modifier.padding(horizontal = 10.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = modifier.padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = taskName, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                color = LightBlue
            )

            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )

            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = null)
            }
        }
    }
}

@Composable
fun WellnessTaskItemList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onClose: (WellnessTask) -> Unit,
    checkChange: (WellnessTask, Boolean) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(list) { task ->
            StatefulWellnessTaskItem(
                taskName = task.label,
                onClose = { onClose(task) },
                checkChange = { checkChange(task, it) }, checked = task.isChecked
            )
        }
    }
}

class WellnessViewModel : ViewModel() {

    private val _tasks = getWellnessTask().toMutableStateList()
    val tasks = _tasks

    private fun getWellnessTask() = List(30) {
        WellnessTask(
            id = it,
            label = "task $it"
        )
    }

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(id: Int, value: Boolean) {
        _tasks.find { it.id == id }?.let {
            it.isChecked = value
        }
    }
}