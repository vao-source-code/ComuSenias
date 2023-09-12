package com.example.comusenias.presentation.component.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun OnBoarding(navController: NavController) {
    val scope= rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {
        val items = OnBoardingItem.get()
        val state = rememberPagerState(pageCount = items.size)

        TopSection(navController)
        HorizontalPager(
            state = state,
            modifier= Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) {page->
            OnBoardingItem(items[page], page = page)
        }
        BottomSection(size = items.size, index = state.currentPage) {
            if (state.currentPage+1 <items.size)
                scope.launch {
                    state.scrollToPage(state.currentPage+1)
                }else {
                    // Navega al LoginScreen
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
            .padding(horizontal = 20.dp)
            .testTag(TestTag.TAG_ONBOARDING_ITEM + page )
    ) {
        Image(
            modifier = Modifier
                .height(250.dp),
            painter = painterResource(item.image),
            contentScale = ContentScale.Fit,
            contentDescription = null)
        Spacer(modifier = Modifier.height(95.dp))
        Text(
            text = stringResource(item.text),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color= blackColorApp,
            fontWeight = FontWeight.SemiBold
        )
    }
}