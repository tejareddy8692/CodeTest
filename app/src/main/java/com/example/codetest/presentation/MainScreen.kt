package com.example.codetest.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.codetest.R
import com.example.codetest.domain.model.Animal
import com.example.codetest.presentation.MainState.*
import com.example.codetest.util.Constants
import com.example.codetest.util.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel, modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(modifier = Modifier.background(Color.Transparent),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.animals),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            when (val state = viewModel.state.value) {
                is Loading -> LoadingScreen()
                is Error -> Toast.makeText(LocalContext.current, state.msg, Toast.LENGTH_SHORT)
                    .show()

                is Animals -> AnimalsList(state.list) { animal ->
                    viewModel.onIntent(MainIntent.AnimalClicked(animal))
                }
            }
        }
    }


    val navigateTo by viewModel.navigateToDetail.collectAsState()
    navigateTo?.let { animal ->
        navController.navigate(Routes.DETAIL_SCREEN.routeName)
        navController.getBackStackEntry(Routes.DETAIL_SCREEN.routeName)
            .savedStateHandle
            .set("animal", animal)
        viewModel.onNavigationDone()
    }


}

@Composable
fun AnimalsList(animals: List<Animal>, onItemClick: (Animal) -> Unit) {
    Box(modifier = Modifier.background(Color.Transparent)) {
        LazyColumn {
            items(items = animals) { animal ->
                AnimalItem(animal = animal, onClick = { onItemClick(animal) })
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                )
            }
        }
    }
}

@Composable
fun AnimalItem(animal: Animal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable { onClick() }
        ) {
            val url = Constants.BASE_URL + animal.image
            val painter = rememberImagePainter(data = url)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 4.dp)
            ) {
                Text(text = animal.name, fontWeight = FontWeight.Bold)
                Text(text = animal.location)
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
