package com.virent.gweather.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.virent.gweather.R
import com.virent.gweather.core.ui.theme.GWeatherTheme
import com.virent.gweather.core.ui.theme.radialGradientBackground
import com.virent.gweather.ui.components.SignIn
import com.virent.gweather.ui.components.SignUp
import com.virent.gweather.viewmodels.LandingViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
object LandingRoute

enum class LandingTabs(@StringRes val tabStringRes: Int) {
    SIGN_UP(R.string.tab_signup),
    SIGN_IN(R.string.tab_login)
}

@Composable
fun LandingScreen(
    openDashboard: () -> Unit,
    showSnackbar: (String) -> Unit,
    viewModel: LandingViewModel = hiltViewModel()
) {
    val currentUser = viewModel.currentUser
    if (currentUser != null) { openDashboard() }
    else { LandingDisplay(openDashboard, showSnackbar) }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LandingDisplay(
    openDashboard: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.imePadding().verticalScroll(scrollState)
            .onFocusedBoundsChanged {
                coroutineScope.launch { scrollState.animateScrollTo(scrollState.maxValue) }
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().aspectRatio(1f).radialGradientBackground()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier.size(LandingDisplayLogoSize)
            )
        }
        Shadow()
        Column(modifier = Modifier.fillMaxWidth().background(color = colorScheme.background)) {
            val pagerState = rememberPagerState(
                initialPage = LandingTabs.SIGN_UP.ordinal,
                pageCount = { LandingTabs.entries.size }
            )
            LandingTabBar(
                selectedTabIndex = pagerState.currentPage,
                onNavigate = { coroutineScope.launch { pagerState.animateScrollToPage(it) } }
            )
            HorizontalPager(pagerState, modifier = Modifier.wrapContentHeight()) { page ->
                when (page) {
                    LandingTabs.SIGN_UP.ordinal -> SignUp(openDashboard, showSnackbar)
                    LandingTabs.SIGN_IN.ordinal -> SignIn(openDashboard, showSnackbar)
                }
            }
        }
    }
}

@Composable
fun LandingTabBar(
    selectedTabIndex: Int = LandingTabs.SIGN_UP.ordinal,
    currentPage: Int = LandingTabs.SIGN_UP.ordinal,
    onNavigate: (Int) -> Unit = {}
) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        LandingTabs.entries.forEach { tab ->
            val tabName = stringResource(tab.tabStringRes)
            Tab(
                text = { Text(tabName, color = colorScheme.onTertiaryContainer) },
                selected = currentPage == selectedTabIndex,
                onClick = { onNavigate(tab.ordinal) },
                modifier = Modifier.background(color = colorScheme.tertiaryContainer)
            )
        }
    }
}

val LandingDisplayLogoSize = 300.dp

@Preview
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() { Column { LandingDisplay({}, {}) } }