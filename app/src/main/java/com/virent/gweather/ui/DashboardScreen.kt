package com.virent.gweather.ui

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.virent.gweather.R
import com.virent.gweather.core.ui.icons.Archive
import com.virent.gweather.core.ui.icons.GWeatherIcons
import com.virent.gweather.core.ui.icons.Today
import com.virent.gweather.core.ui.theme.GWeatherTheme
import com.virent.gweather.core.ui.theme.gradientBackground
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
object DashboardRoute

enum class DashboardTabs(
    @StringRes val tabStringRes: Int,
    val icon: ImageVector
) {
    TODAY(R.string.tab_today, GWeatherIcons.Today),
    ARCHIVE(R.string.tab_archive, GWeatherIcons.Archive)
}

@Composable
fun DashboardScreen(
    toLanding: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = DashboardTabs.TODAY.ordinal,
        pageCount = { DashboardTabs.entries.size }
    )
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        bottomBar = {
            DashboardTabBar(
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
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.gradientBackground().padding(innerPadding)) { page ->
            when (page) {
                DashboardTabs.TODAY.ordinal -> WeatherTodayTab(toLanding, showSnackbar)
                DashboardTabs.ARCHIVE.ordinal -> WeatherArchiveTab()
            }
        }
    }
}

@Composable
fun DashboardTabBar(
    selectedTabIndex: Int,
    onNavigate: (Int) -> Unit = {}
) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        DashboardTabs.entries.forEach { tab ->
            val tabName = stringResource(tab.tabStringRes)
            Tab(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tabName,
                        tint = colorScheme.onTertiaryContainer
                    )
                },
                text = { Text(tabName, color = colorScheme.onTertiaryContainer) },
                selected = tab.ordinal == selectedTabIndex,
                onClick = { onNavigate(tab.ordinal) },
                modifier = Modifier.background(color = colorScheme.tertiaryContainer)
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
        DashboardScreen({}, {})
    }
}