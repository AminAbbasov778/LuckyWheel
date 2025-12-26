package com.example.wheel.components

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpinButton(
    modifier: Modifier,
    onClick: () -> Unit,
    density: Density,
    outerRadius: Float,
) {
    val fontSize = with(density) { (outerRadius * 0.35f).toSp() }


    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(if (pressed) 0.9f else 1f, label = "")


    Box(

        modifier = modifier

            .size(120.dp)

            .scale(scale)

            .pointerInteropFilter {

                when (it.action) {

                    MotionEvent.ACTION_DOWN -> pressed = true

                    MotionEvent.ACTION_UP -> {

                        pressed = false; onClick()

                    }


                    MotionEvent.ACTION_CANCEL -> pressed = false

                }

                true

            }, contentAlignment = Alignment.Center

    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(

                Brush.radialGradient(

                    listOf(

                        Color(0xFF6EE7FF).copy(0.6f),
                        Color(0xFF6EE7FF).copy(0.5f),
                        Color(0xFF6EE7FF).copy(0.5f),
                        Color(0xFF6EE7FF).copy(0.4f),
                        Color(0xFF6EE7FF).copy(0.3f),
                        Color(0xFF6EE7FF).copy(0.2f),
                        Color(0xFF6EE7FF).copy(0.1f),
                        Color(0xFF6EE7FF).copy(0.06f),
                        Color(0xFF6EE7FF).copy(0.05f),
                        Color(0xFF6EE7FF).copy(0.04f),
                        Color(0xFF6EE7FF).copy(0.03f),
                        Color(0xFF6EE7FF).copy(0.02f),
                        Color(0xFF6EE7FF).copy(0.01f),
                        Color(0xFF6EE7FF).copy(0.009f),
                        Color(0xFF6EE7FF).copy(0.005f),
                        Color(0xFF6EE7FF).copy(0.003f),
                        Color(0xFF6EE7FF).copy(0.001f),

                        )

                ),
                radius = size.minDimension * 0.24f

            )

            drawCircle(

                Brush.linearGradient(listOf(Color(0xFF6EE7FF), Color(0xFF4D7CFF))),

                radius = size.minDimension * 0.24f,

                style = Stroke(12f)

            )

            drawCircle(Color(0xFF0F1220), radius = size.minDimension * 0.16f)

        }

        Text("SPIN", fontSize = 22.sp, color = Color.White, fontFamily = FontFamily.Serif)

    }

}
