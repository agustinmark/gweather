package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.ClearSky: ImageVector
    get() {
        if (_ClearSky != null) {
            return _ClearSky!!
        }
        _ClearSky = ImageVector.Builder(
            name = "ClearSky",
            defaultWidth = 125.dp,
            defaultHeight = 125.dp,
            viewportWidth = 33.073f,
            viewportHeight = 33.073f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFAA45))) {
                moveTo(13.894f, 10.686f)
                arcTo(0.528f, 0.528f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13.405f, 10.36f)
                lineTo(12.619f, 8.461f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.978f, -0.405f)
                lineToRelative(0.786f, 1.898f)
                arcToRelative(0.529f, 0.529f, 75.518f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, 0.732f)
                close()
                moveTo(19.965f, 25.343f)
                arcTo(0.528f, 0.528f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.476f, 25.017f)
                lineTo(18.69f, 23.119f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.978f, -0.405f)
                lineToRelative(0.786f, 1.898f)
                arcTo(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.965f, 25.343f)
                close()
                moveTo(8.259f, 20.494f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, -1.018f)
                lineToRelative(1.898f, -0.786f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, 0.978f)
                lineToRelative(-1.898f, 0.786f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 0.04f)
                close()
                moveTo(22.916f, 14.424f)
                arcToRelative(0.529f, 0.529f, 73.309f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, -1.018f)
                lineToRelative(1.898f, -0.786f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, 0.978f)
                lineToRelative(-1.898f, 0.786f)
                arcToRelative(0.54f, 0.54f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 0.04f)
                close()
                moveTo(19.178f, 10.686f)
                arcTo(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 18.69f, 9.954f)
                lineToRelative(0.786f, -1.898f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0.978f, 0.405f)
                lineToRelative(-0.786f, 1.898f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, 0.326f)
                close()
                moveTo(13.107f, 25.343f)
                arcTo(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12.619f, 24.612f)
                lineToRelative(0.786f, -1.898f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0.978f, 0.405f)
                lineToRelative(-0.786f, 1.898f)
                curveToRelative(-0.085f, 0.203f, -0.282f, 0.326f, -0.489f, 0.326f)
                close()
                moveTo(10.157f, 14.424f)
                arcToRelative(0.525f, 0.525f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.202f, -0.04f)
                lineToRelative(-1.898f, -0.786f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, -0.978f)
                lineToRelative(1.898f, 0.786f)
                arcToRelative(0.529f, 0.529f, 115.415f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 1.018f)
                close()
                moveTo(24.813f, 20.494f)
                arcTo(0.525f, 0.525f, 0f, isMoreThanHalf = false, isPositiveArc = true, 24.611f, 20.454f)
                lineToRelative(-1.898f, -0.786f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, -0.978f)
                lineToRelative(1.898f, 0.786f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 1.018f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFAA45))) {
                moveTo(16.536f, 16.537f)
                moveToRelative(-5.359f, 0f)
                arcToRelative(5.359f, 5.359f, 0f, isMoreThanHalf = true, isPositiveArc = true, 10.719f, 0f)
                arcToRelative(5.359f, 5.359f, 0f, isMoreThanHalf = true, isPositiveArc = true, -10.719f, 0f)
            }
            path(fill = SolidColor(Color(0xFFFFDA69))) {
                moveToRelative(16.535f, 12.234f)
                arcToRelative(4.292f, 4.292f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.976f, 2.656f)
                arcToRelative(4.272f, 4.272f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 3.292f)
                arcToRelative(4.273f, 4.273f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2.328f, 2.328f)
                arcToRelative(4.278f, 4.278f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.644f, 0.329f)
                arcToRelative(4.29f, 4.29f, 0f, isMoreThanHalf = false, isPositiveArc = true, -3.976f, -2.656f)
                curveToRelative(-0.908f, -2.191f, 0.137f, -4.712f, 2.328f, -5.62f)
                arcToRelative(4.277f, 4.277f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.644f, -0.329f)
            }
        }.build()

        return _ClearSky!!
    }

@Suppress("ObjectPropertyName")
private var _ClearSky: ImageVector? = null
