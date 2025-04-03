package com.virent.gweather.ui

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.virent.gweather.R
import com.virent.gweather.ui.theme.GWeatherTheme
import kotlinx.coroutines.launch

enum class DashboardTabs(
    @StringRes val tabStringRes: Int,
    @DrawableRes val iconRes: Int,
    val content: @Composable () -> Unit
) {
    TODAY(R.string.tab_today, R.drawable.ic_today, { WeatherToday() }),
    ARCHIVE(R.string.tab_archive, R.drawable.ic_archive, { WeatherArchive() })
}

@Composable
fun DashboardScreen() {
    val pagerState = rememberPagerState(
        initialPage = DashboardTabs.TODAY.ordinal,
        pageCount = { DashboardTabs.entries.size }
    )
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        bottomBar = {
            TabBar(
                selectedTabIndex = pagerState.currentPage,
                onNavigate = { id ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(id)
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HorizontalPager(pagerState, modifier = Modifier.padding(innerPadding)) { page ->
            DashboardTabs.entries[page].content()
        }
    }
}

@Composable
fun TabBar(
    selectedTabIndex: Int = DashboardTabs.TODAY.ordinal,
    currentPage: Int = DashboardTabs.TODAY.ordinal,
    onNavigate: (Int) -> Unit = {}
) {
    TabRow(
        selectedTabIndex = selectedTabIndex
    ) {
        DashboardTabs.entries.forEach { tab ->
            val tabName = stringResource(tab.tabStringRes)
            Tab(
                icon = {
                    Icon(
                        painter = painterResource(tab.iconRes),
                        contentDescription = tabName,
                        tint = colorScheme.onSecondaryContainer
                    )
                },
                text = { Text(tabName, color = colorScheme.onSecondaryContainer) },
                selected = currentPage == selectedTabIndex,
                onClick = { onNavigate(tab.ordinal) },
                modifier = Modifier.background(color = colorScheme.secondaryContainer)
            )
        }
    }
}

@Preview(
    showBackground = false,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = false,
    name = "Dark Mode"
)
@Composable
fun DashboardPreview() {
    GWeatherTheme {
        DashboardScreen()
    }
}