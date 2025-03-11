package com.example.movietime.presentation.moviescreen.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


@Composable
fun CustomEmptyStar(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val center = Offset(width / 2, height / 2)
        val outerRadius = min(width, height) / 2
        val innerRadius = outerRadius * 0.5f

        // Create a star shape with 5 points.
        val starPath = Path().apply {
            val points = 5
            val angle = (2 * Math.PI / (points * 2)).toFloat()
            moveTo(center.x, center.y - outerRadius)
            for (i in 1 until points * 2) {
                val r = if (i % 2 == 0) outerRadius else innerRadius
                val theta = -Math.PI / 2 + i * angle
                lineTo(
                    center.x + r * cos(theta).toFloat(),
                    center.y + r * sin(theta).toFloat()
                )
            }
            close()
        }
        // Draw a filled black star.
        drawPath(
            path = starPath,
            color = Color.Black
        )
        // Draw a gold outline over the star.
        drawPath(
            path = starPath,
            color = Color(0xFFFFD700),
            style = Stroke(width = 2.dp.toPx())
        )
    }
}