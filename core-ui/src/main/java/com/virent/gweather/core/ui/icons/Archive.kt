package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.Archive: ImageVector
    get() {
        if (_Archive != null) {
            return _Archive!!
        }
        _Archive = ImageVector.Builder(
            name = "Archive",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(3f, 3f)
                horizontalLineTo(21f)
                verticalLineTo(7f)
                horizontalLineTo(3f)
                verticalLineTo(3f)
                moveTo(4f, 8f)
                horizontalLineTo(20f)
                verticalLineTo(21f)
                horizontalLineTo(4f)
                verticalLineTo(8f)
                moveTo(9.5f, 11f)
                arcTo(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 9f, 11.5f)
                verticalLineTo(13f)
                horizontalLineTo(15f)
                verticalLineTo(11.5f)
                arcTo(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 14.5f, 11f)
                horizontalLineTo(9.5f)
                close()
            }
        }.build()

        return _Archive!!
    }

@Suppress("ObjectPropertyName")
private var _Archive: ImageVector? = null
