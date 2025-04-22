package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.NightClearSky: ImageVector
    get() {
        if (_NightClearSky != null) {
            return _NightClearSky!!
        }
        _NightClearSky = ImageVector.Builder(
            name = "NightClearSky",
            defaultWidth = 125.dp,
            defaultHeight = 125.dp,
            viewportWidth = 33.073f,
            viewportHeight = 33.073f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFDA69)),
                strokeLineWidth = 0.529166f
            ) {
                moveToRelative(22.607f, 16.805f)
                curveToRelative(0.157f, -3.875f, -2.701f, -7.167f, -6.484f, -7.628f)
                curveToRelative(1.391f, 2.391f, 1.637f, 5.398f, 0.388f, 8.099f)
                curveToRelative(-1.192f, 2.578f, -3.478f, 4.294f, -6.05f, 4.872f)
                arcToRelative(7.352f, 7.352f, 0f, isMoreThanHalf = false, isPositiveArc = false, 4.465f, 1.743f)
                curveToRelative(4.078f, 0.165f, 7.518f, -3.007f, 7.682f, -7.086f)
                close()
            }
        }.build()

        return _NightClearSky!!
    }

@Suppress("ObjectPropertyName")
private var _NightClearSky: ImageVector? = null
