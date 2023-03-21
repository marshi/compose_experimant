package dev.marshi.compose_experimant.uistateexp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
// functionパターン
@Composable
fun SampleComponentCaller(
    onButton2Click: () -> Unit
) {
    val state = remember { SampleComponentCallerState() }
    SampleComponent(
        onButton1Click = {
            state.update("new text")
        },
        onButton2Click = onButton2Click
    )
}

// listenerパターン
@Composable
fun SampleComponentCaller2(
    listener: SampleListener
) {
    val state = remember { SampleComponentCallerState() }
    SampleListenerComponent(
        // 本当はSampleListenerのonButton1Clickでstateを更新したい
        // onButton2Clickではlistenerを呼びたい
        listener = listener
    )
}

@Composable
fun SampleComponent(
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit,
) {
    Column {
        Button(
            onClick = onButton1Click
        ) {
            Text("button")
        }
        Button(
            onClick = onButton2Click
        ) {
            Text("button 2")
        }
    }
}

@Composable
fun SampleListenerComponent(
    listener: SampleListener,
) {
    Column {
        Button(
            onClick = {
                listener.onClick1()
            }
        ) {
            Text("button")
        }
        Button(
            onClick = {
                listener.onClick2()
            }
        ) {
            Text("button 2")
        }
    }
}
