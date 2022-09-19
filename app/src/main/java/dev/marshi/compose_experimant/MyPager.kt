package dev.marshi.compose_experimant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyPager() {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        count = 3,
    ) {
        when (it) {
            0 -> {
                Page1()
            }
            1 -> {
                Page2()
            }
            2 -> {
                Page3()
            }
        }
    }
}

@Composable
fun Page1() {
    Text(modifier = Modifier.background(color = Color.White), text = "Page1")
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