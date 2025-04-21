package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.Snow: ImageVector
    get() {
        if (_Snow != null) {
            return _Snow!!
        }
        _Snow = ImageVector.Builder(
            name = "Snow",
            defaultWidth = 125.dp,
            defaultHeight = 125.dp,
            viewportWidth = 33.073f,
            viewportHeight = 33.073f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFAA45))) {
                moveToRelative(10.857f, 7.069f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, -0.327f)
                lineToRelative(-0.644f, -1.554f)
                arcToRelative(0.53f, 0.53f, 45f, isMoreThanHalf = false, isPositiveArc = true, 0.978f, -0.405f)
                lineToRelative(0.644f, 1.554f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, 0.732f)
                close()
                moveTo(6.245f, 15.098f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, -1.018f)
                lineToRelative(1.554f, -0.644f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, 0.978f)
                lineToRelative(-1.554f, 0.644f)
                arcToRelative(0.54f, 0.54f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 0.04f)
                close()
                moveTo(18.242f, 10.128f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, -1.019f)
                lineToRelative(1.554f, -0.643f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, 0.978f)
                lineToRelative(-1.554f, 0.643f)
                arcToRelative(0.527f, 0.527f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 0.041f)
                close()
                moveTo(15.183f, 7.069f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, -0.732f)
                lineToRelative(0.644f, -1.554f)
                arcToRelative(0.53f, 0.53f, 45f, isMoreThanHalf = false, isPositiveArc = true, 0.978f, 0.405f)
                lineToRelative(-0.644f, 1.554f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.489f, 0.327f)
                close()
                moveTo(7.798f, 10.128f)
                arcToRelative(0.527f, 0.527f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, -0.041f)
                lineToRelative(-1.554f, -0.643f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.405f, -0.978f)
                lineToRelative(1.554f, 0.643f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.203f, 1.019f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFAA45))) {
                moveTo(13.021f, 11.762f)
                moveToRelative(-4.387f, 0f)
                arcToRelative(4.387f, 4.387f, 0f, isMoreThanHalf = true, isPositiveArc = true, 8.775f, 0f)
                arcToRelative(4.387f, 4.387f, 0f, isMoreThanHalf = true, isPositiveArc = true, -8.775f, 0f)
            }
            path(fill = SolidColor(Color(0xFFFFDA69))) {
                moveToRelative(13.019f, 8.432f)
                arcToRelative(3.321f, 3.321f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.077f, 2.056f)
                arcToRelative(3.307f, 3.307f, 74.797f, isMoreThanHalf = false, isPositiveArc = true, 0f, 2.548f)
                arcToRelative(3.307f, 3.307f, 121.786f, isMoreThanHalf = false, isPositiveArc = true, -1.801f, 1.801f)
                arcToRelative(3.31f, 3.31f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.272f, 0.255f)
                arcToRelative(3.321f, 3.321f, 0f, isMoreThanHalf = false, isPositiveArc = true, -3.077f, -2.056f)
                arcToRelative(3.307f, 3.307f, 120.645f, isMoreThanHalf = false, isPositiveArc = true, 0f, -2.548f)
                arcToRelative(3.307f, 3.307f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.801f, -1.801f)
                arcToRelative(3.308f, 3.308f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.272f, -0.255f)
            }
            path(fill = SolidColor(Color(0xFF868686))) {
                moveToRelative(20.686f, 10.969f)
                curveToRelative(-1.898f, 0f, -3.614f, 0.972f, -4.594f, 2.541f)
                arcToRelative(3.974f, 3.974f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4.093f, 2.038f)
                arcToRelative(3.141f, 3.141f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.974f, 3.133f)
                curveToRelative(0f, 1.554f, 1.154f, 2.778f, 2.939f, 3.118f)
                curveToRelative(1.167f, 0.223f, 2.855f, 0.305f, 6.227f, 0.305f)
                curveToRelative(3.187f, 0f, 5.218f, -0.16f, 6.36f, -0.294f)
                curveToRelative(1.679f, -0.197f, 2.806f, -1.371f, 2.806f, -2.922f)
                arcToRelative(2.907f, 2.907f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.248f, -2.392f)
                lineToRelative(0.001f, -0.103f)
                curveTo(26.11f, 13.402f, 23.677f, 10.969f, 20.686f, 10.969f)
                close()
            }
            path(fill = SolidColor(Color(0xFFBFBFBF))) {
                moveToRelative(24.988f, 17.111f)
                arcToRelative(4.403f, 4.403f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.064f, -0.718f)
                arcToRelative(4.365f, 4.365f, 0f, isMoreThanHalf = true, isPositiveArc = false, -8.412f, -1.634f)
                arcToRelative(2.909f, 2.909f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.958f, 1.915f)
                arcToRelative(2.049f, 2.049f, 135f, isMoreThanHalf = false, isPositiveArc = false, -0.519f, -0.073f)
                arcToRelative(2.079f, 2.079f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.079f, 2.079f)
                curveToRelative(0f, 1.148f, 0.923f, 1.858f, 2.079f, 2.079f)
                curveToRelative(1.156f, 0.221f, 2.92f, 0.287f, 6.029f, 0.287f)
                curveToRelative(3.109f, 0f, 5.109f, -0.155f, 6.237f, -0.287f)
                curveToRelative(1.128f, -0.132f, 1.871f, -0.838f, 1.871f, -1.871f)
                arcToRelative(1.865f, 1.865f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.312f, -1.777f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE0E0E0))) {
                moveToRelative(12.163f, 17.677f)
                curveToRelative(0.18f, 0f, 0.352f, 0.03f, 0.519f, 0.073f)
                arcToRelative(2.909f, 2.909f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.958f, -1.915f)
                arcToRelative(4.364f, 4.364f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.047f, -2.732f)
                curveToRelative(2.224f, 0f, 4.056f, 1.664f, 4.327f, 3.814f)
                arcToRelative(4.085f, 4.085f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.039f, -0.525f)
                arcToRelative(4.365f, 4.365f, 0f, isMoreThanHalf = true, isPositiveArc = false, -8.412f, -1.634f)
                arcToRelative(2.909f, 2.909f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.958f, 1.915f)
                arcToRelative(2.049f, 2.049f, 135f, isMoreThanHalf = false, isPositiveArc = false, -0.519f, -0.073f)
                arcToRelative(2.079f, 2.079f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.079f, 2.079f)
                curveToRelative(0f, 0.19f, 0.034f, 0.365f, 0.081f, 0.531f)
                curveToRelative(0.241f, -0.882f, 1.039f, -1.534f, 1.998f, -1.534f)
                close()
            }
            path(fill = SolidColor(Color(0xFF45ABFF))) {
                moveToRelative(12.173f, 22.773f)
                curveToRelative(-0.505f, 0f, -0.917f, 0.407f, -0.926f, 0.91f)
                arcToRelative(0.928f, 0.928f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.448f, -0.116f)
                curveToRelative(-0.331f, 0f, -0.638f, 0.178f, -0.803f, 0.464f)
                curveToRelative(-0.252f, 0.437f, -0.106f, 0.997f, 0.325f, 1.256f)
                arcToRelative(0.927f, 0.927f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.477f, 1.72f)
                curveToRelative(0.157f, 0f, 0.312f, -0.04f, 0.449f, -0.116f)
                curveToRelative(0.008f, 0.503f, 0.421f, 0.91f, 0.926f, 0.91f)
                curveToRelative(0.505f, 0f, 0.917f, -0.407f, 0.926f, -0.91f)
                arcToRelative(0.928f, 0.928f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.251f, -0.348f)
                curveToRelative(0.252f, -0.438f, 0.106f, -0.997f, -0.325f, -1.256f)
                arcToRelative(0.927f, 0.927f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.478f, -1.72f)
                curveToRelative(-0.157f, 0f, -0.311f, 0.04f, -0.448f, 0.116f)
                arcToRelative(0.928f, 0.928f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.926f, -0.91f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveToRelative(13.349f, 26.424f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0.397f, -0.687f)
                lineToRelative(-0.779f, -0.45f)
                lineToRelative(0.779f, -0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, -0.397f, -0.687f)
                lineToRelative(-0.779f, 0.45f)
                lineToRelative(0f, -0.9f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.794f, 0f)
                lineToRelative(0f, 0.9f)
                lineToRelative(-0.779f, -0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.397f, 0.687f)
                lineToRelative(0.779f, 0.45f)
                lineToRelative(-0.779f, 0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0.397f, 0.687f)
                lineToRelative(0.779f, -0.45f)
                lineToRelative(0f, 0.9f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.794f, 0f)
                lineToRelative(0f, -0.9f)
                close()
            }
            path(fill = SolidColor(Color(0xFF45ABFF))) {
                moveToRelative(18.192f, 22.773f)
                curveToRelative(-0.505f, 0f, -0.917f, 0.407f, -0.926f, 0.91f)
                arcToRelative(0.926f, 0.926f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.926f, 1.603f)
                arcToRelative(0.927f, 0.927f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.477f, 1.72f)
                curveToRelative(0.157f, 0f, 0.312f, -0.04f, 0.449f, -0.116f)
                curveToRelative(0.008f, 0.503f, 0.421f, 0.91f, 0.926f, 0.91f)
                curveToRelative(0.505f, 0f, 0.917f, -0.407f, 0.926f, -0.91f)
                arcToRelative(0.928f, 0.928f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.251f, -0.348f)
                curveToRelative(0.252f, -0.438f, 0.106f, -0.997f, -0.325f, -1.256f)
                arcToRelative(0.927f, 0.927f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.477f, -1.72f)
                curveToRelative(-0.157f, 0f, -0.312f, 0.04f, -0.449f, 0.116f)
                arcToRelative(0.926f, 0.926f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.926f, -0.91f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveToRelative(19.765f, 25.737f)
                lineToRelative(-0.779f, -0.45f)
                lineToRelative(0.779f, -0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, -0.397f, -0.687f)
                lineToRelative(-0.779f, 0.45f)
                lineToRelative(0f, -0.9f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.794f, 0f)
                lineToRelative(0f, 0.9f)
                lineToRelative(-0.779f, -0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.397f, 0.687f)
                lineToRelative(0.779f, 0.45f)
                lineToRelative(-0.779f, 0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0.397f, 0.687f)
                lineToRelative(0.779f, -0.45f)
                lineToRelative(0f, 0.9f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.794f, 0f)
                lineToRelative(0f, -0.9f)
                lineToRelative(0.779f, 0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0.396f, -0.687f)
                close()
            }
            path(fill = SolidColor(Color(0xFF45ABFF))) {
                moveToRelative(24.211f, 22.773f)
                curveToRelative(-0.505f, 0f, -0.917f, 0.407f, -0.926f, 0.91f)
                arcToRelative(0.926f, 0.926f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.926f, 1.603f)
                arcToRelative(0.927f, 0.927f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.477f, 1.72f)
                curveToRelative(0.157f, 0f, 0.312f, -0.04f, 0.449f, -0.116f)
                curveToRelative(0.008f, 0.503f, 0.421f, 0.91f, 0.926f, 0.91f)
                curveToRelative(0.505f, 0f, 0.917f, -0.407f, 0.926f, -0.91f)
                arcToRelative(0.928f, 0.928f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.251f, -0.348f)
                curveToRelative(0.252f, -0.438f, 0.106f, -0.997f, -0.325f, -1.256f)
                arcTo(0.927f, 0.927f, 0f, isMoreThanHalf = false, isPositiveArc = false, 25.585f, 23.567f)
                curveToRelative(-0.157f, 0f, -0.312f, 0.04f, -0.449f, 0.116f)
                arcToRelative(0.927f, 0.927f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.926f, -0.91f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveToRelative(25.784f, 25.737f)
                lineToRelative(-0.779f, -0.45f)
                lineToRelative(0.779f, -0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, -0.397f, -0.687f)
                lineToRelative(-0.779f, 0.45f)
                lineToRelative(0f, -0.9f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.794f, 0f)
                lineToRelative(0f, 0.9f)
                lineTo(23.035f, 24.149f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.397f, 0.687f)
                lineToRelative(0.779f, 0.45f)
                lineToRelative(-0.779f, 0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0.397f, 0.687f)
                lineToRelative(0.779f, -0.45f)
                lineToRelative(0f, 0.9f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.794f, 0f)
                lineToRelative(0f, -0.9f)
                lineToRelative(0.779f, 0.45f)
                arcToRelative(0.397f, 0.397f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0.396f, -0.687f)
                close()
            }
        }.build()

        return _Snow!!
    }

@Suppress("ObjectPropertyName")
private var _Snow: ImageVector? = null
