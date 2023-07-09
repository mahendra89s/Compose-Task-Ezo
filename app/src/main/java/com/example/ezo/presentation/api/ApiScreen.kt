package com.example.ezo.presentation.api

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ezo.model.ItemData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiScreen(
    apiScreenViewModel: ApiScreenViewModel = hiltViewModel()
) {
    val uiState = apiScreenViewModel.items.collectAsState()

    LaunchedEffect(key1 = ""){
        apiScreenViewModel.fetchItems()
    }

    Scaffold(
        topBar = {
            TopBar()
        },
        content = {paddingValues ->
            when(uiState.value){
                is ApiScreenUiState.Loading ->{
                    Box(
                        modifier = Modifier.padding(paddingValues).fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator(
                            color = Color.Gray
                        )
                    }
                }
                is ApiScreenUiState.Data ->{
                    ListScreen(
                        data = (uiState.value as ApiScreenUiState.Data).data,
                        paddingValues = paddingValues
                    )
                }
                else -> {

                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {
            Text(text = "Shrine", fontSize = 16.sp, color = Color.White)
        },
        navigationIcon = {
            Icon(Icons.Filled.Menu,"menu", tint = Color.White)
        },
        actions = {
            Icon(Icons.Filled.Search,"search" , tint = Color.White)
            Spacer(modifier = Modifier.width(20.dp))
            Icon(Icons.Filled.Settings,"settings" , tint = Color.White)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.DarkGray)
    )
}

@Composable
fun ListScreen(
    data : List<ItemData>,
    paddingValues : PaddingValues
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(paddingValues)
    ){
        items(data.size){
            Card(
                modifier = Modifier.padding(10.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = data[it].url),
                        contentDescription = "image",
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        text = data[it].itemName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 10.dp),
                        text = data[it].itemPrice.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}