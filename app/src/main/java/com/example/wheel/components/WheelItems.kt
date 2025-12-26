package com.example.wheel.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.wheel.model.WheelModel
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WheelItems(
    segments: List<WheelModel>,
    centerX: Float,
    centerY: Float,
    outerRadius: Float,
    innerRadius: Float,
) {
    val count = segments.size
    if (count == 0) return

    val angle = (2 * PI / count).toFloat()
    val gapAngle = 0.05f
    val middleRadius = (innerRadius + outerRadius) / 2

    val density = LocalDensity.current
    val itemSizeDp = with(density) { (outerRadius * 0.35f).toDp() }
    val itemHalfSizePx = with(density) { itemSizeDp.toPx() / 2f }
    val fontSize = with(density) { (outerRadius * 0.06f).toSp() }

    segments.forEachIndexed { index, model ->
        val reverseIndex = count - index - 1
        val middleAngle =
            (angle * reverseIndex - PI.toFloat() / 2 - angle / 2 + gapAngle / 2) + (angle - gapAngle) / 2


        val exactX = centerX + middleRadius * cos(middleAngle)
        val exactY = centerY + middleRadius * sin(middleAngle)


        val finalOffsetX = (exactX - itemHalfSizePx).toInt()
        val finalOffsetY = (exactY - itemHalfSizePx).toInt()

        val imagePainter = rememberAsyncImagePainter(
            model = model.img
        )

        Box(
            modifier = Modifier
                .offset { IntOffset(finalOffsetX, finalOffsetY) }
                .size(itemSizeDp)
                .rotate(Math.toDegrees(middleAngle.toDouble()).toFloat() + 90f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                if (model.img != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .fillMaxHeight(0.4f)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = imagePainter,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Text(
                    text = model.name,
                    fontSize = fontSize,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }
}