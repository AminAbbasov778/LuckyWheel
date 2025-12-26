package com.example.wheel.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WheelCanvas(
    centerX: Float,
    centerY: Float,
    outerRadius: Float,
    innerRadius: Float,
    count: Int,
    strokeWidth: Float,
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        if (count == 0) return@Canvas

        val center = Offset(centerX, centerY)
        val angle = (2 * PI / count).toFloat()
        val gapAngle = 0.09f
        val cornerRadius = outerRadius * 0.08f

        drawCircle(Color(0xFF4D7CFF), radius = outerRadius + (strokeWidth * 1.5f), center = center)
        drawCircle(Color(0xFF0A0A0C), radius = outerRadius + strokeWidth, center = center)
        drawCircle(Color(0xFF202126), radius = innerRadius - strokeWidth, center = center)

        repeat(count) { index ->
            val reverseIndex = count - index - 1
            val start = angle * reverseIndex - PI.toFloat() / 2 - angle / 2 + gapAngle / 2
            val sweep = angle - gapAngle

            val path = Path().apply {
                arcTo(
                    Rect(
                        center.x - outerRadius,
                        center.y - outerRadius,
                        center.x + outerRadius,
                        center.y + outerRadius
                    ),
                    Math.toDegrees(start.toDouble()).toFloat(),
                    Math.toDegrees(sweep.toDouble()).toFloat(),
                    false
                )
                quadraticBezierTo(
                    center.x + (outerRadius - cornerRadius) * cos(start + sweep),
                    center.y + (outerRadius - cornerRadius) * sin(start + sweep),
                    center.x + (innerRadius + cornerRadius) * cos(start + sweep),
                    center.y + (innerRadius + cornerRadius) * sin(start + sweep)
                )
                arcTo(
                    Rect(
                        center.x - innerRadius,
                        center.y - innerRadius,
                        center.x + innerRadius,
                        center.y + innerRadius
                    ),
                    Math.toDegrees((start + sweep).toDouble()).toFloat(),
                    -Math.toDegrees(sweep.toDouble()).toFloat(),
                    false
                )
                quadraticBezierTo(
                    center.x + (innerRadius + cornerRadius) * cos(start),
                    center.y + (innerRadius + cornerRadius) * sin(start),
                    center.x + (outerRadius - cornerRadius) * cos(start),
                    center.y + (outerRadius - cornerRadius) * sin(start)
                )
                close()
            }
            drawPath(path, color = Color(0xFF1A1B23))
            drawPath(path, color = Color(0xFF3A3B43), style = Stroke(width = strokeWidth * 0.2f))
        }
    }


}
