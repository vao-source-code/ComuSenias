package com.example.comusenias.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.domain.library.chapter.ProvideChapter
import com.example.comusenias.presentation.ui.theme.LETS_GO_LEARN
import com.example.comusenias.presentation.ui.theme.QUANTITY_COLUMNS
import com.example.comusenias.presentation.ui.theme.SIZE200
import com.example.comusenias.presentation.ui.theme.SIZE22
import com.example.comusenias.presentation.ui.theme.SIZE26
import com.example.comusenias.presentation.ui.theme.SIZE60
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size12
import com.example.comusenias.presentation.ui.theme.size16
import com.example.comusenias.presentation.ui.theme.size20

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier) {
    HomeScreenExample(
        navController = navController, modifier = modifier
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenExample(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(size16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.profile_avatar),
                contentDescription = "painter image",
                modifier = Modifier
                    .size(SIZE60.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = LETS_GO_LEARN,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = SIZE22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(size10.dp))
            Image(
                painter = painterResource(id = R.drawable.level),
                contentDescription = "painter image",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            ChapterView(modifier = modifier)
        }
    }
}

@Composable
fun ChapterView(modifier: Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(QUANTITY_COLUMNS),
    ) {
        items(getChapterItem()) { chapter ->
            CardExampleDos(chapter = chapter) {
            }
        }
    }
}


@SuppressLint("NewApi")
@Composable
fun CardExampleDos(chapter: ProvideChapter, onItemSelected: (ProvideChapter) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = chapter.color
        ),
        modifier = Modifier.padding(size12.dp),
        shape = RoundedCornerShape(SIZE26.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = size20.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(size12.dp)
                .background(chapter.color)
        ) {
            Image(
                painter = painterResource(chapter.image),
                contentDescription = "My Image",
                modifier = Modifier
                    .size(SIZE200.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(chapter.color),
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = chapter.title,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = size16.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}

fun getChapterItem(): List<ProvideChapter> {
    return listOf(
        ProvideChapter("ChapterOne", "Vocales", R.drawable.senia_imagen_dos, Color(0xFFE57373)),
        ProvideChapter("ChapterOne", "Vocales", R.drawable.senia_imagen_dos, Color(0xFFF66666)),
        ProvideChapter("ChapterOne", "Vocales", R.drawable.senia_imagen_dos, Color(0xFFF66666)),
        ProvideChapter("ChapterOne", "Vocales", R.drawable.senia_imagen_dos, Color(0xFFE57373)),
        ProvideChapter("ChapterOne", "Vocales", R.drawable.senia_imagen_dos, Color(0xFFE57373)),
        ProvideChapter("ChapterOne", "Vocales", R.drawable.senia_imagen_dos, Color(0xFFF66666)),
        ProvideChapter("ChapterOne", "Vocales", R.drawable.senia_imagen_dos, Color(0xFFF66666)),
        ProvideChapter("ChapterOne", "Vocales", R.drawable.senia_imagen_dos, Color(0xFFE57373)),
    )
}