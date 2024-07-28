package ar.com.scacchipa.eldarwallet.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(
    viewModel: NavigationViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        val credentialStatus by viewModel.getCredStatusFlow().collectAsState()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = MaterialTheme.colors.secondary
                )
            }) {
            tabRowItems.forEachIndexed { index, tabRowItem ->
                Tab(
                    enabled = when(index) {
                        0 -> true
                        else -> credentialStatus.isUserLogged
                    },
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    icon = {
                        Icon(
                            modifier = modifier.size(width = 28.dp, height = 28.dp),
                            painter = painterResource(id = tabRowItem.icon),
                            contentDescription = tabRowItem.title
                        )
                    },
                    text = {
                        Text(
                            text = tabRowItem.title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    })
            }
        }

        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }
}
