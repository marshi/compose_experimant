package dev.marshi.compose_experimant

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.IntOffset

@Composable
fun OffsetScreen() {

    var offset by remember { mutableStateOf(IntOffset(0, 0)) }
    val animateOffset by animateIntOffsetAsState(
        targetValue = offset,
        animationSpec = SpringSpec()
    )

    Column {
        Text(modifier = Modifier.offset { animateOffset }, text = "text")
        Text(modifier = Modifier.drawBehind { animateOffset }, text = "text")

        Button(onClick = {
            if (offset == IntOffset(0, 0)) {
                offset = IntOffset(400, 400)
            } else {
                offset = IntOffset(0, 0)
            }
        }) {
            Text("Button")
        }
    }
}
