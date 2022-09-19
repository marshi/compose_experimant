package dev.marshi.compose_experimant

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import com.google.accompanist.pager.HorizontalPager

/**
 * テキストの幅がレイアウトの幅よりも長いときにmarquee効果を効果を効かせる.
 *
 * 内部的には、Textを２つ横並びに作りそれらをアニメーションさせて横に流している.
 *
 */
@Composable
fun MarqueeText(
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    durationMsPerWidth: Int = 7500,
    delayMs: Int = 3000,
    spaceRatio: Float = 0.3f,
) {
    val createText = @Composable { localModifier: Modifier ->
        Text(
            text,
            textAlign = textAlign,
            modifier = localModifier,
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = 1,
            onTextLayout = onTextLayout,
            style = style,
        )
    }
    val textLayoutInfoState = remember { mutableStateOf<TextLayoutInfo?>(null) }
    val textLayoutInfo = textLayoutInfoState.value
    val transition = rememberInfiniteTransition()
    val initialValue = 0
    val offset by if (textLayoutInfo != null) {
        val duration = durationMsPerWidth * textLayoutInfo.textWidth / textLayoutInfo.containerWidth
        transition.animateValue(
            initialValue = initialValue,
            targetValue = -textLayoutInfo.textWidth,
            typeConverter = Int.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = duration,
                    delayMillis = delayMs,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart
            ),
        )
    } else {
        remember { mutableStateOf(initialValue) }
    }

    SubcomposeLayout(
        modifier = modifier.clipToBounds()
    ) { constraints ->
        val infiniteWidthConstraints = constraints.copy(maxWidth = Constraints.Infinity)
        var mainText: Placeable = subcompose(MarqueeLayers.MainText) {
            createText(textModifier)
        }.first().measure(infiniteWidthConstraints)

        var secondPlaceableWithOffset: Pair<Placeable, Int>? = null
        if (mainText.width <= constraints.maxWidth) {
            mainText = subcompose(MarqueeLayers.SecondaryText) {
                createText(textModifier)
            }.first().measure(constraints)
            textLayoutInfoState.value = null
        } else {
            val spacing = (constraints.maxWidth * spaceRatio).toInt()
            textLayoutInfoState.value = TextLayoutInfo(
                textWidth = mainText.width + spacing, // テキストの長さ + widthの2/3の長さ
                containerWidth = constraints.maxWidth // 画面幅
            )
            // offsetは `0 ~ -(mainText.width + spacing)`の間を変化する.
            // したがって、secondTextOffsetの値は `0 ~ (mainText.width + spacing)` の間を変化する.
            // これはmarquee効果で流れてくるテキストの左端の位置を表す.
            val secondTextOffset = mainText.width + spacing + offset
            val secondTextSpace = constraints.maxWidth - secondTextOffset
            if (secondTextSpace > 0) {
                // marquee効果で流れてくるテキストの左端の位置が描画範囲内にある場合
                secondPlaceableWithOffset = subcompose(MarqueeLayers.SecondaryText) {
                    createText(textModifier)
                }.first().measure(constraints) to secondTextOffset
            }
        }

        layout(
            width = constraints.maxWidth,
            height = mainText.height
        ) {
            mainText.place(offset, 0)
            secondPlaceableWithOffset?.let { (placable, secondTextOffset) ->
                placable.place(secondTextOffset, 0)
            }
        }
    }
}

@Preview(widthDp = 100)
@Composable
fun MarqeeTextPreview() {
    Column {
        MarqueeText(text = "aa")
        MarqueeText(text = "aiueoaiueoaiueoaiueoaiueoaiueoaiueoaiueo")
    }
}

private enum class MarqueeLayers { MainText, SecondaryText }
private data class TextLayoutInfo(val textWidth: Int, val containerWidth: Int)
