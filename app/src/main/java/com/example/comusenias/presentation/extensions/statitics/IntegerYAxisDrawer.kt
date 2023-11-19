package com.example.comusenias.presentation.extensions.statitics

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.github.tehras.charts.bar.renderer.yaxis.LabelFormatter
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.YAxisDrawer

class IntegerYAxisDrawer(
    val labelValueFormatter: LabelFormatter = { value -> "${value.toInt()}" },

    ) : YAxisDrawer {

    private val simpleYAxisDrawer: SimpleYAxisDrawer

    init {
        simpleYAxisDrawer = SimpleYAxisDrawer(
            labelValueFormatter = labelValueFormatter,
        )
    }

    override fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        minValue: Float,
        maxValue: Float
    ) {
        simpleYAxisDrawer.drawAxisLabels(drawScope, canvas, drawableArea, minValue, maxValue)
    }

    override fun drawAxisLine(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect
    ) {
        simpleYAxisDrawer.drawAxisLine(drawScope, canvas, drawableArea)
    }
}
