package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.ShowerRain: ImageVector
    get() {
        if (_ShowerRain != null) {
            return _ShowerRain!!
        }
        _ShowerRain = ImageVector.Builder(
            name = "ShowerRain",
            defaultWidth = 125.dp,
            defaultHeight = 125.dp,
            viewportWidth = 33.073f,
            viewportHeight = 33.073f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF868686)),
                strokeLineWidth = 0.529f
            ) {
                moveToRelative(13.435f, 8.542f)
                arcToRelative(4.769f, 4.769f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4.263f, 2.646f)
                arcToRelative(3.708f, 3.708f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.675f, 3.704f)
                curveToRelative(0f, 1.966f, 1.6f, 3.311f, 3.104f, 3.578f)
                curveToRelative(1.162f, 0.206f, 2.501f, 0.302f, 4.215f, 0.302f)
                curveToRelative(1.704f, 0f, 2.811f, -0.011f, 4.038f, -0.233f)
                curveToRelative(2.166f, -0.391f, 2.489f, -1.927f, 2.489f, -2.808f)
                curveToRelative(0f, -0.914f, -0.439f, -1.755f, -1.149f, -2.285f)
                curveToRelative(0.002f, -0.047f, 0.002f, -0.095f, 0.002f, -0.142f)
                curveTo(18.197f, 10.679f, 16.061f, 8.542f, 13.435f, 8.542f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                strokeLineWidth = 0.529f
            ) {
                moveTo(17.066f, 14.032f)
                arcTo(3.704f, 3.704f, 62.51f, isMoreThanHalf = false, isPositiveArc = false, 13.435f, 9.6f)
                curveToRelative(-1.711f, 0f, -3.147f, 1.162f, -3.573f, 2.739f)
                arcToRelative(2.618f, 2.618f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.66f, -0.093f)
                arcToRelative(2.646f, 2.646f, 135f, isMoreThanHalf = false, isPositiveArc = false, -2.646f, 2.646f)
                curveToRelative(0f, 1.462f, 1.239f, 2.36f, 2.231f, 2.536f)
                curveToRelative(0.992f, 0.176f, 2.245f, 0.286f, 4.031f, 0.286f)
                curveToRelative(1.786f, 0f, 2.749f, -0.017f, 3.849f, -0.216f)
                curveToRelative(1.1f, -0.199f, 1.619f, -0.769f, 1.619f, -1.766f)
                arcToRelative(1.799f, 1.799f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.219f, -1.699f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 0.529f
            ) {
                moveToRelative(9.201f, 13.349f)
                curveToRelative(0.23f, 0f, 0.448f, 0.039f, 0.66f, 0.093f)
                arcTo(3.702f, 3.702f, 45f, isMoreThanHalf = false, isPositiveArc = true, 13.435f, 10.703f)
                curveToRelative(1.858f, 0f, 3.391f, 1.368f, 3.659f, 3.151f)
                curveToRelative(0.027f, -0.18f, 0.045f, -0.362f, 0.045f, -0.55f)
                arcToRelative(3.704f, 3.704f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.704f, -3.704f)
                curveToRelative(-1.711f, 0f, -3.147f, 1.162f, -3.573f, 2.739f)
                arcToRelative(2.618f, 2.618f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.66f, -0.093f)
                arcToRelative(2.646f, 2.646f, 135f, isMoreThanHalf = false, isPositiveArc = false, -2.646f, 2.646f)
                curveToRelative(0f, 0.191f, 0.023f, 0.371f, 0.062f, 0.543f)
                arcTo(2.644f, 2.644f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9.201f, 13.349f)
                close()
            }
            path(fill = SolidColor(Color(0xFF868686))) {
                moveToRelative(20.904f, 10.969f)
                curveToRelative(-1.898f, 0f, -3.614f, 0.972f, -4.594f, 2.541f)
                arcToRelative(3.974f, 3.974f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4.093f, 2.038f)
                arcToRelative(3.141f, 3.141f, 135f, isMoreThanHalf = false, isPositiveArc = false, -2.974f, 3.133f)
                curveToRelative(0f, 1.554f, 1.154f, 2.778f, 2.939f, 3.118f)
                curveToRelative(1.167f, 0.223f, 2.855f, 0.305f, 6.227f, 0.305f)
                curveToRelative(3.187f, 0f, 5.217f, -0.16f, 6.36f, -0.294f)
                curveToRelative(1.679f, -0.197f, 2.806f, -1.371f, 2.806f, -2.922f)
                arcToRelative(2.907f, 2.907f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.248f, -2.392f)
                lineToRelative(0.001f, -0.103f)
                curveTo(26.328f, 13.402f, 23.895f, 10.969f, 20.904f, 10.969f)
                close()
            }
            path(fill = SolidColor(Color(0xFF45ABFF))) {
                moveToRelative(13.553f, 28.442f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.496f, -0.715f)
                lineToRelative(0.397f, -1.058f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.991f, 0.372f)
                lineToRelative(-0.397f, 1.058f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.495f, 0.343f)
                close()
                moveTo(14.743f, 25.267f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.496f, -0.715f)
                lineToRelative(0.397f, -1.058f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0.991f, 0.372f)
                lineToRelative(-0.397f, 1.058f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.495f, 0.343f)
                close()
                moveTo(20.828f, 28.442f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.496f, -0.715f)
                lineToRelative(0.397f, -1.058f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.991f, 0.372f)
                lineToRelative(-0.397f, 1.058f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.495f, 0.343f)
                close()
                moveTo(22.019f, 25.267f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.496f, -0.714f)
                lineToRelative(0.396f, -1.058f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.991f, 0.371f)
                lineToRelative(-0.396f, 1.058f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.495f, 0.343f)
                close()
                moveTo(16.926f, 29.5f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.496f, -0.715f)
                lineToRelative(0.397f, -1.058f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.991f, 0.372f)
                lineToRelative(-0.397f, 1.058f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.495f, 0.343f)
                close()
                moveTo(18.117f, 26.325f)
                arcTo(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 17.621f, 25.61f)
                lineToRelative(0.397f, -1.058f)
                arcToRelative(0.53f, 0.53f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.991f, 0.372f)
                lineToRelative(-0.397f, 1.058f)
                arcToRelative(0.529f, 0.529f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.495f, 0.343f)
                close()
            }
            path(fill = SolidColor(Color(0xFFBFBFBF))) {
                moveToRelative(25.206f, 17.111f)
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
            path(fill = SolidColor(Color(0xFFE0E0E0))) {
                moveToRelative(12.381f, 17.677f)
                curveToRelative(0.18f, 0f, 0.352f, 0.03f, 0.519f, 0.073f)
                arcToRelative(2.909f, 2.909f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.958f, -1.915f)
                arcToRelative(4.364f, 4.364f, 135f, isMoreThanHalf = false, isPositiveArc = true, 4.047f, -2.732f)
                curveToRelative(2.224f, 0f, 4.056f, 1.664f, 4.327f, 3.814f)
                arcToRelative(4.085f, 4.085f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.039f, -0.525f)
                arcToRelative(4.365f, 4.365f, 0f, isMoreThanHalf = true, isPositiveArc = false, -8.412f, -1.634f)
                arcToRelative(2.909f, 2.909f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.958f, 1.915f)
                arcToRelative(2.049f, 2.049f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.519f, -0.073f)
                arcToRelative(2.079f, 2.079f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.079f, 2.079f)
                curveToRelative(0f, 0.19f, 0.034f, 0.365f, 0.081f, 0.531f)
                curveToRelative(0.241f, -0.882f, 1.039f, -1.534f, 1.998f, -1.534f)
                close()
            }
        }.build()

        return _ShowerRain!!
    }

@Suppress("ObjectPropertyName")
private var _ShowerRain: ImageVector? = null
