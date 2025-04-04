package com.virent.gweather.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.virent.gweather.R
import com.virent.gweather.ui.models.LandingViewModel
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
    if (currentUser != null) {
        openDashboard()
    } else {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .paint(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
            }
            Shadow()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorScheme.background)
            ) {
                val pagerState = rememberPagerState(
                    initialPage = LandingTabs.SIGN_UP.ordinal,
                    pageCount = { LandingTabs.entries.size }
                )
                val coroutineScope = rememberCoroutineScope()
                LandingTabBar(
                    selectedTabIndex = pagerState.currentPage,
                    onNavigate = { id ->
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(id)
                        }
                    }
                )
                HorizontalPager(pagerState, modifier = Modifier.weight(1f)) { page ->
                    when (page) {
                        LandingTabs.SIGN_UP.ordinal -> SignUp(openDashboard, showSnackbar)
                        LandingTabs.SIGN_IN.ordinal -> SignIn(openDashboard, showSnackbar)
                    }
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
    TabRow(
        selectedTabIndex = selectedTabIndex
    ) {
        LandingTabs.entries.forEach { tab ->
            val tabName = stringResource(tab.tabStringRes)
            Tab(
                text = { Text(tabName, color = colorScheme.onSecondaryContainer) },
                selected = currentPage == selectedTabIndex,
                onClick = { onNavigate(tab.ordinal) },
                modifier = Modifier.background(color = colorScheme.secondaryContainer)
            )
        }
    }
}