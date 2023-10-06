package com.example.comusenias.presentation.component.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.example.comusenias.presentation.ui.theme.HAVENT_ACCOUNT
import com.example.comusenias.presentation.ui.theme.REGISTER
import com.example.comusenias.presentation.ui.theme.size14
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size5

@Composable
fun FooterContent(navController: NavHostController, modifier: Modifier) {
    Row(
        modifier = modifier, horizontalArrangement = Arrangement.Center

    ) {
        Text(text = HAVENT_ACCOUNT, fontSize = size14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.width(size5.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = AppScreen.RegisterScreen.route)
            },
            text = REGISTER,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = size14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFooterContent() {
    ComuSeniasTheme() {
        FooterContent(
            navController = rememberNavController(),
            Modifier
                .fillMaxWidth()
                .padding(bottom = size20.dp)
        )
    }
}