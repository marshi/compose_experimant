package dev.marshi.compose_experimant

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ScaledThirdBrush(val shaderBrush: ShaderBrush) : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        return shaderBrush.createShader(size / 1f)
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ScaledThirdBrush(text: String) {
    val colors = listOf(Color(0x44ffffff), Color.White, Color(0x44ffffff))
    val currentFontSizePx = with(LocalDensity.current) { 50.sp.toPx() }
    val currentFontSizeDoublePx = currentFontSizePx * 2
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = -100f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(
            tween(1000, delayMillis = 1000, easing = LinearEasing),
        ),
    )
    Box(
        Modifier
            .size(100.dp)
//            .background(
//                brush = Brush.linearGradient(
//                    colors,
//                    start = Offset(offset, offset),
//                    end = Offset(offset + 100, offset + 100),
//                    tileMode = TileMode.Decal,
//                ),
//                alpha = 0.5f
//            ),
    ) {
        Text(
            text = text,
            color = Color(0x44ffffff),
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors,
                    start = Offset(offset, offset),
                    end = Offset(offset + 100, offset + 100),
                    tileMode = TileMode.Clamp,
                ),
//                brush = Brush.linearGradient(
//                    colors,
//                    start = Offset(offset, offset),
//                    end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
//                    tileMode = TileMode.Repeated,
//                )
            )
        )
    }
}
