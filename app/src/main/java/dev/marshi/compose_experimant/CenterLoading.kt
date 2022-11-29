package dev.marshi.compose_experimant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CenterLoading() {
    val density = LocalDensity.current
    val state = rememberLazyListState()
    val headerHeight = 300.dp
    val headerBottomOffsetDp by remember {
        derivedStateOf {
            val header = state.layoutInfo.visibleItemsInfo.find { it.key == "header" }
                ?: return@derivedStateOf -headerHeight
            with(density) { header.offset.toDp() }
        }
    }

    println(headerBottomOffsetDp)
    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = state,
        ) {
            item(key = "header") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight)
                        .background(Color.Red)
                ) {
                    Text("Header")
                }
            }
            List(10) {
                item {
                    Text(modifier = Modifier.height(100.dp), text = "aiueo")
                }
            }
        }
        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = headerHeight + headerBottomOffsetDp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun CenterLoadingPreview() {
    CenterLoading()
}