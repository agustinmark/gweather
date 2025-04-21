package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.Datetime: ImageVector
    get() {
        if (_Datetime != null) {
            return _Datetime!!
        }
        _Datetime = ImageVector.Builder(
            name = "Datetime",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(15f, 13f)
                horizontalLineTo(16.5f)
                verticalLineTo(15.82f)
                lineTo(18.94f, 17.23f)
                lineTo(18.19f, 18.53f)
                lineTo(15f, 16.69f)
                verticalLineTo(13f)
                moveTo(19f, 8f)
                horizontalLineTo(5f)
                verticalLineTo(19f)
                horizontalLineTo(9.67f)
                curveTo(9.24f, 18.09f, 9f, 17.07f, 9f, 16f)
                arcTo(7f, 7f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 9f)
                curveTo(17.07f, 9f, 18.09f, 9.24f, 19f, 9.67f)
                verticalLineTo(8f)
                moveTo(5f, 21f)
                curveTo(3.89f, 21f, 3f, 20.1f, 3f, 19f)
                verticalLineTo(5f)
                curveTo(3f, 3.89f, 3.89f, 3f, 5f, 3f)
                horizontalLineTo(6f)
                verticalLineTo(1f)
                horizontalLineTo(8f)
                verticalLineTo(3f)
                horizontalLineTo(16f)
                verticalLineTo(1f)
                horizontalLineTo(18f)
                verticalLineTo(3f)
                horizontalLineTo(19f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 21f, 5f)
                verticalLineTo(11.1f)
                curveTo(22.24f, 12.36f, 23f, 14.09f, 23f, 16f)
                arcTo(7f, 7f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 23f)
                curveTo(14.09f, 23f, 12.36f, 22.24f, 11.1f, 21f)
                horizontalLineTo(5f)
                moveTo(16f, 11.15f)
                arcTo(4.85f, 4.85f, 0f, isMoreThanHalf = false, isPositiveArc = false, 11.15f, 16f)
                curveTo(11.15f, 18.68f, 13.32f, 20.85f, 16f, 20.85f)
                arcTo(4.85f, 4.85f, 0f, isMoreThanHalf = false, isPositiveArc = false, 20.85f, 16f)
                curveTo(20.85f, 13.32f, 18.68f, 11.15f, 16f, 11.15f)
                close()
            }
        }.build()

        return _Datetime!!
    }

@Suppress("ObjectPropertyName")
private var _Datetime: ImageVector? = null
