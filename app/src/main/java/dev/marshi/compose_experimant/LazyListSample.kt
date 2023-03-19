package dev.marshi.compose_experimant

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LazyListSample() {
    val state = rememberLazyListState()

    val index by remember {
        derivedStateOf {
            println("call: derivedStateof")
            state.firstVisibleItemIndex
        }
    }

    println("call: LazyListSample")

    LazyColumn(state = state) {
        List(100) {
            item {
                Text(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    text = "${index}: aiueo",
                )
            }
        }
    }
}

@Preview
@Composable
fun LazyListSamplePreview() {
    LazyListSample()
}