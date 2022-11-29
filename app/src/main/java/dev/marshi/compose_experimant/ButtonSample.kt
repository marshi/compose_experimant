package dev.marshi.compose_experimant

import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonSample() {
    Button(modifier = Modifier.size(1.dp), onClick = {}) {
        Text("aiueo")
    }
}

@Preview
@Composable
fun ButtonSamplePreview() {
    ButtonSample()
}