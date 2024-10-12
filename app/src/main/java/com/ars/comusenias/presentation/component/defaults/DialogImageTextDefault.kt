package com.ars.comusenias.presentation.component.defaults

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ars.comusenias.R
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE28
import com.ars.comusenias.presentation.ui.theme.SIZE60
import com.ars.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun DialogImageTextDefault(
    icon : Int,
    title : String,
    text : String,
    onDismissRequest: () -> Unit,
    onButtonOk : () -> Unit

) {

    Dialog(onDismissRequest = onDismissRequest) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(SIZE16.dp))
        ) {

            Column(
                modifier = Modifier
                    .padding(SIZE10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Email Icon",
                    modifier = Modifier.size(SIZE60.dp),
                    tint = Color(0xFF7C4DFF)
                )

                Spacer(modifier = Modifier.height(SIZE16.dp))

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = SIZE28.sp,
                    color = primaryColorApp
                )

                Spacer(modifier = Modifier.height(SIZE10.dp))

                Text(
                    text =text,
                    textAlign = TextAlign.Center,
                    fontSize = SIZE14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(SIZE10.dp))

                ButtonApp(titleButton = "OK", enabledButton = true, onClickButton = onButtonOk)

            }
        }
    }
}