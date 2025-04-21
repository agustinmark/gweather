package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.TempMax: ImageVector
    get() {
        if (_TempMax != null) {
            return _TempMax!!
        }
        _TempMax = ImageVector.Builder(
            name = "TempMax",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(12f, 22f)
                arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 12f)
                arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 2f)
                arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, 22f, 12f)
                arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 22f)
                moveTo(17f, 14f)
                lineTo(12f, 9f)
                lineTo(7f, 14f)
                horizontalLineTo(17f)
                close()
            }
        }.build()

        return _TempMax!!
    }

@Suppress("ObjectPropertyName")
private var _TempMax: ImageVector? = null
