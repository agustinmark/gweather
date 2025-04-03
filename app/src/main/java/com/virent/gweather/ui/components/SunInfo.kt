package com.virent.gweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.virent.gweather.R
import com.virent.gweather.utils.asTimeString


@Composable
fun SunInfo(
    offset: Int,
    sunrise: Long,
    sunset: Long
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.secondaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(all = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = colorScheme.secondaryContainer)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sunrise),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = colorScheme.onSecondaryContainer
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = sunrise.asTimeString(offset),
                    style = typography.headlineLarge,
                    fontWeight = FontWeight.Medium,
                    color = colorScheme.onSecondaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_sunrise),
                    style = typography.bodySmall,
                    color = colorScheme.onSecondaryContainer
                )
            }
            VerticalDivider(
                color = colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .height(32.dp)
                    .alpha(0.6f)
            )
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = sunset.asTimeString(offset),
                    style = typography.headlineLarge,
                    fontWeight = FontWeight.Medium,
                    color = colorScheme.onSecondaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_sunset),
                    style = typography.bodySmall,
                    color = colorScheme.onSecondaryContainer
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_sunset),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = colorScheme.onSecondaryContainer
            )
        }
    }
}