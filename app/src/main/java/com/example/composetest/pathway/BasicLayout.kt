package com.example.composetest.pathway

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.DrawableStringPair
import com.example.composetest.R
import com.example.composetest.Utils.alignYourBodyData
import com.example.composetest.Utils.favoriteCollectionsData
import com.example.composetest.ui.theme.ComposeTestTheme

@Composable
private fun SearchBar(modifier: Modifier = Modifier) {

    TextField(
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        value = "",
        placeholder = {
            Text(text = stringResource(id = R.string.search_bar_place_holder))
        },
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchBar() {
    ComposeTestTheme {
        SearchBar()
    }
}

@Composable
private fun AlignYourBody(modifier: Modifier = Modifier, data: DrawableStringPair) {

    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = data.drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(88.dp)
        )

        Text(
            text = stringResource(id = data.text),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .paddingFromBaseline(
                    top = 24.dp, bottom = 8.dp
                )
        )
    }
}

@Composable
private fun AlignYourBodyList(modifier: Modifier = Modifier, data: List<DrawableStringPair>) {
    LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp), content = {
        items(data) { item ->
            AlignYourBody(data = item)
        }
    }, contentPadding = PaddingValues(horizontal = 16.dp))
}

@Preview(showBackground = true)
@Composable
private fun PreviewAlignYourBody() {
    ComposeTestTheme {

        AlignYourBody(data = alignYourBodyData.first())
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAlignYourBodyList() {
    ComposeTestTheme {

        AlignYourBodyList(data = alignYourBodyData)
    }
}

@Composable
private fun FavoritesCollectionCard(modifier: Modifier = Modifier, data: DrawableStringPair) {

    Surface(modifier = modifier, shape = MaterialTheme.shapes.small) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(192.dp)) {
            Image(
                painter = painterResource(id = data.drawable),
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringResource(id = data.text),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun FavoritesCollectionCardList(
    modifier: Modifier = Modifier,
    data: List<DrawableStringPair>
) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier
            .height(120.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data) { item ->
            FavoritesCollectionCard(data = item)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
private fun PreviewFavoriteCollectionCard() {

    ComposeTestTheme() {
        Surface(modifier = Modifier.padding(all = 8.dp)) {
            FavoritesCollectionCard(
                modifier = Modifier.height(56.dp),
                data = favoriteCollectionsData.first()
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
private fun PreviewFavoriteCollectionCardList() {

    ComposeTestTheme() {
        Surface(modifier = Modifier.padding(all = 8.dp)) {
            FavoritesCollectionCardList(data = favoriteCollectionsData)
        }
    }
}

@Composable
private fun MainBottomNav(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background
    ) {
        BottomNavigationItem(selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            }
        )

        BottomNavigationItem(selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            }
        )

    }
}

@Preview
@Composable
private fun PreviewMainBottomNav() {
    ComposeTestTheme() {
        MainBottomNav()
    }
}

@Composable
private fun SlotsHome(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title).uppercase(),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
private fun HomeContent(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar()

        SlotsHome(R.string.align_your_body) {
            AlignYourBodyList(data = alignYourBodyData)
        }

        SlotsHome(R.string.favorite_collections) {
            FavoritesCollectionCardList(data = favoriteCollectionsData)
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
private fun PreviewHomeLayout() {
    ComposeTestTheme(darkTheme = true) {
        HomeContent()
    }
}

@Composable
fun HomeContent() {
    Scaffold(bottomBar = { MainBottomNav() }) {
        HomeContent(Modifier.padding(it))
    }
}