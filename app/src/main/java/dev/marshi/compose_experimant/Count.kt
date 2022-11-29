package dev.marshi.compose_experimant

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CountScreen() {
    Count(10)
}

@Composable
fun Count(
    initial: Int = 0,
) {
    var count by remember { mutableStateOf(initial) }
    Surface {
        Column {
            Text(text = count.toString())
            Button(onClick = { count++ }) {
                Text("Up")
            }
        }
    }
}

@Preview
@Composable
fun CountPreview() {
    Count(10)
}