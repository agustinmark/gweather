package com.virent.gweather.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GWeatherIcons.Mist: ImageVector
    get() {
        if (_Mist != null) {
            return _Mist!!
        }
        _Mist = ImageVector.Builder(
            name = "Mist",
            defaultWidth = 125.dp,
            defaultHeight = 125.dp,
            viewportWidth = 33.073f,
            viewportHeight = 33.073f
        ).apply {
            path(fill = SolidColor(Color(0xFFBFBFBF))) {
                moveToRelative(19.031f, 10.969f)
                curveToRelative(-1.898f, 0f, -3.614f, 0.972f, -4.594f, 2.541f)
                arcToRelative(3.974f, 3.974f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4.093f, 2.037f)
                arcToRelative(3.141f, 3.141f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.974f, 3.133f)
                curveToRelative(0f, 1.554f, 1.154f, 2.778f, 2.939f, 3.118f)
                curveToRelative(1.167f, 0.223f, 2.855f, 0.305f, 6.227f, 0.305f)
                curveToRelative(3.187f, 0f, 5.217f, -0.16f, 6.36f, -0.294f)
                curveToRelative(1.679f, -0.197f, 2.806f, -1.371f, 2.806f, -2.922f)
                arcToRelative(2.907f, 2.907f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.248f, -2.392f)
                lineTo(24.455f, 16.394f)
                curveTo(24.455f, 13.402f, 22.022f, 10.969f, 19.031f, 10.969f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveToRelative(23.35f, 16.98f)
                arcToRelative(4.207f, 4.207f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.047f, -0.587f)
                arcToRelative(4.339f, 4.339f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.026f, -0.471f)
                lineToRelative(-12.012f, 0f)
                arcToRelative(2.916f, 2.916f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.331f, 0.752f)
                arcToRelative(2.049f, 2.049f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.519f, -0.073f)
                curveToRelative(-0.444f, 0f, -0.854f, 0.142f, -1.192f, 0.379f)
                close()
                moveTo(24.644f, 18.888f)
                curveToRelative(0f, -0.307f, -0.076f, -0.595f, -0.207f, -0.849f)
                lineToRelative(-15.896f, 0f)
                curveToRelative(-0.066f, 0.203f, -0.111f, 0.416f, -0.111f, 0.641f)
                curveToRelative(0f, 0.146f, 0.016f, 0.285f, 0.044f, 0.417f)
                lineToRelative(16.152f, 0f)
                curveToRelative(0.007f, -0.069f, 0.019f, -0.136f, 0.019f, -0.209f)
                close()
                moveTo(9.147f, 20.155f)
                curveToRelative(0.364f, 0.297f, 0.835f, 0.504f, 1.36f, 0.604f)
                curveToRelative(1.156f, 0.221f, 2.92f, 0.287f, 6.029f, 0.287f)
                curveToRelative(3.109f, 0f, 5.109f, -0.155f, 6.237f, -0.287f)
                curveToRelative(0.567f, -0.067f, 1.036f, -0.278f, 1.364f, -0.604f)
                close()
                moveTo(16.658f, 12.747f)
                lineToRelative(4.771f, 0f)
                arcToRelative(4.344f, 4.344f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.398f, -0.719f)
                arcToRelative(4.328f, 4.328f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.616f, 0.884f)
                curveToRelative(0.083f, -0.053f, 0.156f, -0.116f, 0.243f, -0.165f)
                close()
                moveTo(23.116f, 14.863f)
                arcToRelative(4.348f, 4.348f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.575f, -1.058f)
                lineToRelative(-7.011f, 0f)
                arcToRelative(4.326f, 4.326f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.545f, 0.954f)
                arcToRelative(2.904f, 2.904f, 45f, isMoreThanHalf = false, isPositiveArc = false, -1.15f, -0.237f)
                curveToRelative(-0.493f, 0f, -0.956f, 0.125f, -1.362f, 0.341f)
                close()
            }
        }.build()

        return _Mist!!
    }

@Suppress("ObjectPropertyName")
private var _Mist: ImageVector? = null
