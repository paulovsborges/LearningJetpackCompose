package com.example.composetest.pathway

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.R
import com.example.composetest.ui.theme.ComposeTestTheme
import kotlinx.coroutines.launch

@Composable
fun TextElement(name: String) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val elementPadding by animateDpAsState(
        targetValue = if (isExpanded) 50.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {

        Row(modifier = Modifier.padding(all = 20.dp)) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = elementPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello,")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
                )
                if (isExpanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4)
                    )
                }
            }

            IconButton(onClick = { isExpanded = !isExpanded }) {

                val icon = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore

                val description =
                    if (isExpanded) stringResource(id = R.string.btn_show_more_text)
                    else stringResource(id = R.string.btn_show_less_text)

                Icon(imageVector = icon, contentDescription = description)
            }
        }
    }
}

@Composable
fun ColumnElement(
    names: List<String> = List(1000) {
        "$it"
    },
    listState: LazyListState = LazyListState(),
    onBtnClicked: () -> Unit
) {

    Column() {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {

            Button(
                onClick = onBtnClicked
            ) {
                Text(text = "Back to top ")
            }
        }
        LazyColumn(modifier = Modifier.padding(all = 8.dp), state = listState) {
            items(items = names) { name ->
                TextElement(name = name)
            }
        }
    }
}

@Composable
fun OnBoardingScreen(onBtnClicked: () -> Unit) {

    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(text = "Welcome")
            Button(
                onClick = onBtnClicked,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun HandleAppStart() {

    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    val listState = LazyListState()


    if (shouldShowOnBoarding) {
        OnBoardingScreen() {
            shouldShowOnBoarding = false
        }
    } else {

        ColumnElement(listState = listState) {

            coroutineScope.launch {
                listState.animateScrollToItem(0, 1)
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true)
@Composable
private fun Preview1() {
    ComposeTestTheme {
//        ColumnElement()
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingPreview() {
    ComposeTestTheme {
        OnBoardingScreen({})
    }
}