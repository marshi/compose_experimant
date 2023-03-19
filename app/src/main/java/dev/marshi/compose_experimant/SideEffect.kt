package dev.marshi.compose_experimant

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SideScreen() {
    var username by remember { mutableStateOf("default") }
    Column {
        Side(username = username)
        Button(onClick = { username += "a" }) {
            Text("Button")
        }
    }
}

@Composable
fun Side(username: String) {
    SideEffect {
        println("sideeffect $username")
    }
    println("hello : $username")
    Text(username)
}