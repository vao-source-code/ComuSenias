package com.ars.comusenias.presentation.component.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.navigation.AuthScreen
import com.ars.comusenias.presentation.ui.theme.FORGOT_PASSWORD
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.primaryColorApp
import com.ars.comusenias.presentation.view_model.LoginViewModel

@Composable
fun RememberMeAndForgetMyPass(viewModel: LoginViewModel, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SIZE10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        ForgetMyPass { navController.navigate(AuthScreen.ResetPasswordScreen.route) }
    }
}

@Composable
fun ForgetMyPass(onClickText: () -> Unit = {}) {
    Text(
        modifier = Modifier
            .testTag(TestTag.TAG_FORGET_MY_PASS)
            .clickable { onClickText() },
        text = FORGOT_PASSWORD,
        color = primaryColorApp,
        fontSize = SIZE14.sp,
        fontWeight = FontWeight.Bold
    )
}

