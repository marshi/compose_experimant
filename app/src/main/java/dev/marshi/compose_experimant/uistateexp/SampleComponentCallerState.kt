package dev.marshi.compose_experimant.uistateexp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SampleComponentCallerState {
    var text by mutableStateOf("text")

    fun update(new: String) {
        text = new
    }
}