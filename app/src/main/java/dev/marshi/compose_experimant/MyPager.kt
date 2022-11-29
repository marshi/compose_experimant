package dev.marshi.compose_experimant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyPager() {
    val pagerState = rememberPagerState()
    val stateMap: MutableMap<Int, LazyListState> by remember {
        mutableMapOf()
    }
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        count = 8,
    ) {
        println("pager $it")
        Column() {
            Text("page $it", color = Color.White)
//            if (((pagerState.currentPage - 1)..(pagerState.currentPage + 1)).contains(it)) {
            Page1()
//            } else {
//                Text("")
//            }
            when (it) {
//            0 -> {
//                Page1()
//            }
//            1 -> {
//                Page1()
//            }
//            2 -> {
//                Page1()
//            }
            }
        }
    }
}

@Composable
fun Page1() {
    println("pager")
    val state = remember { LazyListState(0, 0) }
    LazyRow(state = state) {
        repeat(30) {
            item {
                Text(
                    modifier = Modifier
                        .height(100.dp)
                        .background(color = Color.White),
                    text = "Page ${it}"
                )
            }
        }
    }
}

@Composable
fun Page2() {
    Text(modifier = Modifier.background(color = Color.White), text = "Page2")
}

@Composable
fun Page3() {
    Text(modifier = Modifier.background(color = Color.White), text = "Page3")
}

@Preview
@Composable
fun PagerPreview() {
    MyPager()
}
