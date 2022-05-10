package com.example.composetest

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.Utils.conversationSample
import com.example.composetest.ui.theme.ComposeTestTheme

@Composable
fun Test(text: String, text2: String) {

    Row(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colors.primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember {
            mutableStateOf(false)
        }

        val colorState by animateColorAsState(
            targetValue =
            if (isExpanded) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

            val maxLines = if (isExpanded) Int.MAX_VALUE else 1

            Text(
                text = text,
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = colorState,
                modifier = Modifier
                    .animateContentSize()
                    .padding(all = 1.dp)
            ) {

                Text(
                    text = text2,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = maxLines,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun ExpansesList(messages: List<Message>) {

    LazyColumn {
        items(messages) { message ->
            Test(text = message.title, text2 = message.message)
        }
    }
}

@Preview
@Composable
fun PreviewTest() {

    ComposeTestTheme() {

        Test(text = "hallo", text2 = "Android")
    }
}

@Composable
@Preview
fun PreviewList() {

    ComposeTestTheme() {

        ExpansesList(conversationSample)

    }
}
