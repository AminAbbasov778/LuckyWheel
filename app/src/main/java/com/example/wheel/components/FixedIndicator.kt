package com.example.wheel.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun FixedIndicator(
    centerX: Float,
    centerY: Float,
    outerRadius: Float,
    innerRadius: Float,
    count: Int,
    strokeWidth: Float,
    indicatorSize: Float,
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        if (count == 0) return@Canvas

        val center = Offset(centerX, centerY)
        val angle = (2 * PI / count).toFloat()
        val gapAngle = 0.09f

        val start = -PI.toFloat() / 2 - angle / 2 + gapAngle / 2
        val sweep = angle - gapAngle
        val cornerRadius = outerRadius * 0.08f

        val selectedPath = Path().apply {
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

        drawPath(
            path = selectedPath,
            color = Color(0xFF757984),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        val middleAngle = start + sweep / 2
        val triangleCenter = Offset(
            center.x + innerRadius * cos(middleAngle),
            center.y + innerRadius * sin(middleAngle)
        )

        val triangle = Path().apply {
            moveTo(
                triangleCenter.x + indicatorSize * cos(middleAngle),
                triangleCenter.y + indicatorSize * sin(middleAngle)
            )
            lineTo(
                triangleCenter.x - indicatorSize * sin(middleAngle),
                triangleCenter.y + indicatorSize * cos(middleAngle)
            )
            lineTo(
                triangleCenter.x + indicatorSize * sin(middleAngle),
                triangleCenter.y - indicatorSize * cos(middleAngle)
            )
            close()
        }
        drawPath(triangle, color = Color(0xFF757984))
    }
}
