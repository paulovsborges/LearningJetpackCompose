package com.example.composetest.pathway

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.pathway.statecodelab.WellnessTaskItemModel
import com.example.composetest.ui.theme.ComposeTestTheme

@Composable
fun MainStateCodeLabScreenInstance() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        WellnessScreen()

    }
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        StateFulWaterCount()

        val tasks = remember {
            generateTaskList().toMutableStateList()
        }
        WellnessTaskList(tasks)
    }
}

@Composable
fun StateLessWaterCount(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        if (count > 0) {
            Text(
                text = "You have had $count glasses",
                modifier = modifier.padding(all = 16.dp),
                color = Color.Black
            )
        }

        Button(
            onClick = onIncrement,
            modifier = Modifier.padding(top = 8.dp),
            enabled = count < 10
        ) {
            Text(text = "Add one")
        }
    }
}

@Composable
fun StateFulWaterCount(modifier: Modifier = Modifier) {

    var count by remember {
        mutableStateOf(0)
    }

    StateLessWaterCount(
        count, {
            count++
        },
        modifier = modifier
    )
}

@Composable
fun StatefulWellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String
) {

    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }

    StatelessWellnessTask(
        taskName = taskName,
        isChecked = isChecked,
        changeCheck = {
            isChecked = it

        },
        onClose = {

        })
}

@Composable
fun StatelessWellnessTask(
    taskName: String,
    isChecked: Boolean,
    changeCheck: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = taskName, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )

        Checkbox(
            checked = isChecked, onCheckedChange = changeCheck
        )

        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = null)
        }
    }
}

@Composable
fun WellnessTaskList(
    modifier: Modifier = Modifier,
    list: List<WellnessTaskItemModel>
) {

    LazyColumn(modifier = modifier) {
        items(list) {
            StatefulWellnessTaskItem(taskName = it.name)
        }
    }

}

private fun generateTaskList() = List(30) {
    WellnessTaskItemModel(it, "task # $it")
}

@Composable
@Preview(showBackground = true)
fun StatefulWellnessTaskItemPreview() {
//    StatefulWellnessTaskItem()
}

@Composable
@Preview
fun WellnessScreenPreview() {
    ComposeTestTheme {
        MainStateCodeLabScreenInstance()
    }
}