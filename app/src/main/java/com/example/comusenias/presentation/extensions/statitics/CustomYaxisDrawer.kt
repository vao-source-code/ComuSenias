package com.example.comusenias.presentation.extensions.statitics

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.YAxisDrawer

class CustomYaxisDrawer(
    labelValueFormatter: (Float) -> String = { value -> value.toString() },
) : YAxisDrawer {

    private val simpleYAxisDrawer = SimpleYAxisDrawer(
        labelValueFormatter = labelValueFormatter,
    )

    override fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        minValue: Float,
        maxValue: Float
    ) {
        simpleYAxisDrawer.drawAxisLabels(drawScope, canvas, drawableArea, minValue, maxValue)
    }

    override fun drawAxisLine(drawScope: DrawScope, canvas: Canvas, drawableArea: Rect) {
        simpleYAxisDrawer.drawAxisLine(drawScope, canvas, drawableArea)
    }
}