package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.Clouds: ImageVector
    get() {
        if (_Clouds != null) {
            return _Clouds!!
        }
        _Clouds = ImageVector.Builder(
            name = "Clouds",
            defaultWidth = 125.dp,
            defaultHeight = 125.dp,
            viewportWidth = 33.073f,
            viewportHeight = 33.073f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFAA45)),
                strokeLineWidth = 0.529f
            ) {
                moveToRelative(10.857f, 10.317f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, -0.327f)
                lineToRelative(-0.644f, -1.554f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.978f, -0.405f)
                lineToRelative(0.644f, 1.554f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, 0.732f)
                close()
                moveTo(6.245f, 18.345f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, -1.018f)
                lineToRelative(1.554f, -0.644f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, 0.978f)
                lineToRelative(-1.554f, 0.644f)
                arcToRelative(0.54f, 0.54f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 0.04f)
                close()
                moveTo(18.242f, 13.375f)
                arcTo(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 18.04f, 12.357f)
                lineToRelative(1.554f, -0.643f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, 0.978f)
                lineToRelative(-1.554f, 0.643f)
                arcToRelative(0.527f, 0.527f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 0.041f)
                close()
                moveTo(15.183f, 10.317f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, -0.732f)
                lineToRelative(0.644f, -1.554f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.978f, 0.405f)
                lineToRelative(-0.644f, 1.554f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, 0.327f)
                close()
                moveTo(7.798f, 13.375f)
                arcToRelative(0.527f, 0.527f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, -0.041f)
                lineToRelative(-1.554f, -0.643f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, -0.978f)
                lineToRelative(1.554f, 0.643f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 1.019f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFAA45)),
                strokeLineWidth = 0.529f
            ) {
                moveTo(13.021f, 15.009f)
                moveToRelative(-4.387f, 0f)
                arcToRelative(4.387f, 4.387f, 0f, isMoreThanHalf = true, isPositiveArc = true, 8.775f, 0f)
                arcToRelative(4.387f, 4.387f, 0f, isMoreThanHalf = true, isPositiveArc = true, -8.775f, 0f)
            }
            path(
                fill = SolidColor(Color(0xFFFFDA69)),
                strokeLineWidth = 0.529f
            ) {
                moveToRelative(13.019f, 11.679f)
                arcToRelative(3.321f, 3.321f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.077f, 2.056f)
                arcToRelative(3.307f, 3.307f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 2.548f)
                arcToRelative(3.307f, 3.307f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.801f, 1.801f)
                arcToRelative(3.31f, 3.31f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.272f, 0.255f)
                arcToRelative(3.321f, 3.321f, 0f, isMoreThanHalf = false, isPositiveArc = true, -3.077f, -2.056f)
                arcToRelative(3.307f, 3.307f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -2.548f)
                arcToRelative(3.307f, 3.307f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.801f, -1.801f)
                arcToRelative(3.308f, 3.308f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.272f, -0.255f)
            }
            path(
                fill = SolidColor(Color(0xFF868686)),
                strokeLineWidth = 0.529f
            ) {
                moveToRelative(20.686f, 14.216f)
                curveToRelative(-1.898f, 0f, -3.614f, 0.972f, -4.594f, 2.541f)
                arcToRelative(3.974f, 3.974f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4.093f, 2.037f)
                arcToRelative(3.141f, 3.141f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.974f, 3.133f)
                curveToRelative(0f, 1.554f, 1.154f, 2.778f, 2.939f, 3.118f)
                curveToRelative(1.167f, 0.223f, 2.855f, 0.305f, 6.227f, 0.305f)
                curveToRelative(3.187f, 0f, 5.217f, -0.16f, 6.36f, -0.294f)
                curveToRelative(1.679f, -0.197f, 2.806f, -1.371f, 2.806f, -2.922f)
                arcToRelative(2.907f, 2.907f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.248f, -2.392f)
                lineToRelative(0.001f, -0.103f)
                curveTo(26.11f, 16.649f, 23.677f, 14.216f, 20.686f, 14.216f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE6E6E6)),
                strokeLineWidth = 0.529f
            ) {
                moveToRelative(24.988f, 20.358f)
                arcToRelative(4.403f, 4.403f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.064f, -0.718f)
                arcToRelative(4.365f, 4.365f, 0f, isMoreThanHalf = true, isPositiveArc = false, -8.412f, -1.634f)
                arcToRelative(2.909f, 2.909f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.958f, 1.915f)
                arcToRelative(2.049f, 2.049f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.519f, -0.073f)
                arcToRelative(2.079f, 2.079f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.079f, 2.079f)
                curveToRelative(0f, 1.148f, 0.923f, 1.858f, 2.079f, 2.079f)
                curveToRelative(1.156f, 0.221f, 2.92f, 0.287f, 6.029f, 0.287f)
                curveToRelative(3.109f, 0f, 5.109f, -0.155f, 6.237f, -0.287f)
                curveToRelative(1.128f, -0.132f, 1.871f, -0.838f, 1.871f, -1.871f)
                arcToRelative(1.865f, 1.865f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.312f, -1.777f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 0.529f
            ) {
                moveToRelative(12.163f, 20.924f)
                curveToRelative(0.18f, 0f, 0.352f, 0.03f, 0.519f, 0.073f)
                arcToRelative(2.909f, 2.909f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.958f, -1.915f)
                arcToRelative(4.364f, 4.364f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.047f, -2.732f)
                curveToRelative(2.224f, 0f, 4.056f, 1.664f, 4.327f, 3.814f)
                arcToRelative(4.085f, 4.085f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.039f, -0.525f)
                arcToRelative(4.365f, 4.365f, 0f, isMoreThanHalf = true, isPositiveArc = false, -8.412f, -1.634f)
                arcToRelative(2.909f, 2.909f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.958f, 1.915f)
                arcToRelative(2.049f, 2.049f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.519f, -0.073f)
                arcToRelative(2.079f, 2.079f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.079f, 2.079f)
                curveToRelative(0f, 0.191f, 0.034f, 0.365f, 0.081f, 0.531f)
                curveToRelative(0.241f, -0.882f, 1.039f, -1.534f, 1.998f, -1.534f)
                close()
            }
        }.build()

        return _Clouds!!
    }

@Suppress("ObjectPropertyName")
private var _Clouds: ImageVector? = null
