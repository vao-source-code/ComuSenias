package com.example.comusenias.presentation.component.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.comusenias.constants.TestTag
import com.example.comusenias.domain.models.OnBoardingItem
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.IMAGE
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.size08
import com.example.comusenias.presentation.ui.theme.size1
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size24
import com.example.comusenias.presentation.ui.theme.size250
import com.example.comusenias.presentation.ui.theme.size95
import com.example.comusenias.presentation.view_model.BottomBarViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun OnBoarding(navController: NavController) {
    val scope = rememberCoroutineScope()
    val bottomBarViewModel = remember { BottomBarViewModel() }

    Column(Modifier.fillMaxSize()) {
        val items = OnBoardingItem.get()
        val state = rememberPagerState(pageCount = items.size)

        TopSection(navController)
        HorizontalPager(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .weight(size08)
        ) { page ->
            OnBoardingItem(items[page], page = page)
        }
        BottomSection(size = items.size, index = state.currentPage) {
            if (state.currentPage + size1 < items.size)
                scope.launch {
                    state.scrollToPage(state.currentPage + size1)
                } else {
                navController.popBackStack()
                bottomBarViewModel.isBottomAppBarVisible.value = true
                navController.navigate(AppScreen.LoginScreen.route)
            }
        }
    }
}

@Composable
fun OnBoardingItem(
    item: OnBoardingItem,
    page: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = size20.dp)
            .testTag(TestTag.TAG_ONBOARDING_ITEM + page)
    ) {
        Image(
            modifier = Modifier
                .height(size250.dp)
                .width(size250.dp),
            painter = painterResource(item.image),
            contentScale = ContentScale.Fit,
            contentDescription = IMAGE
        )
        Spacer(modifier = Modifier.height(size95.dp))
        Text(
            text = stringResource(item.text),
            textAlign = TextAlign.Center,
            fontSize = size24.sp,
            color = blackColorApp,
            fontWeight = FontWeight.SemiBold
        )
    }
}