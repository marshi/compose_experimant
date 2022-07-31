package dev.marshi.compose_experimant

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.SnapSpec
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun List() {
    LazyColumn() {
        item {
            AnimatedContentSample()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentSample() {
    var count by remember { mutableStateOf(0) }
    Column {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(text = "$targetCount")
        }
        Button(onClick = { count++ }) {
            Text("button")
        }
        Row() {
            MarqueeText(
                text = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
                modifier = Modifier.width(200.dp)
            )
            Text("-------| ")
            Text("-------")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Slide() {
    val width = 96.dp
    val menuWidth = 30.dp

    val swipeableState = rememberSwipeableState(0, confirmStateChange = {
        println(it)
        true
    })
    val density = LocalDensity.current
    val menuWidthPx = with(density) { menuWidth.toPx() }
    val anchors = mapOf(0f to 0, -menuWidthPx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal,
            )
            .background(Color.LightGray)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            val alpha = if (swipeableState.progress.to == 0) {
                1 - swipeableState.progress.fraction
            } else {
                swipeableState.progress.fraction
            }
            Text(
                modifier = Modifier
                    .width(30.dp)
                    .alpha(alpha)
                    .align(Alignment.CenterEnd),
                text = "aiueoaiuaiueo",
            )
            Box(
                Modifier
                    .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.DarkGray)
            )
        }
    }
}

@Preview
@Composable
fun SlidePreview() {
    Slide()
}

@Preview
@Composable
fun AnimPreview() {
    AnimatedContentSample()
}
