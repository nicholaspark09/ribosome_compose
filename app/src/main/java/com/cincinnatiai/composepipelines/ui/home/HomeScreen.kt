package com.cincinnatiai.composepipelines.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cincinnatiai.composepipelines.R
import com.cincinnatiai.composepipelines.model.Category

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    var isDropdownExpanded by remember {
        mutableStateOf(false)
    }
    var selectedOption: Category? by rememberSaveable {
        mutableStateOf(null)
    }
    val isLoading: Boolean by homeViewModel.isLoading.collectAsState()
    val categoriesState by homeViewModel.categoryState.collectAsState()
    val pipelines by homeViewModel.pipelines.collectAsState()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.start()
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.ribosome),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
            }, backgroundColor = Color.White, actions = {
                CategoryDropDown(categoriesState) { selectedCategory ->
                    homeViewModel.fetchPipelines(selectedCategory)
                }
            })
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(color = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxSize(),
            ) {
                var i = 0
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    items(items = pipelines, key = { item -> item.id }) { item ->
                        PipelineRow(tag = "Item:$i", item = item) {
                            homeViewModel.navigateToPipeline(it, navController = navController)
                        }
                        ++i
                    }
                }
            }
            if (isLoading) {
                CircularProgressIndicator(
                    modifier =
                        Modifier.align(Alignment.Center)
                            .testTag("loadingIndicator")
                )
            }
        }
    }

}

@Composable
fun errorScreen(error: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = error))
    }
}

@Composable
fun loadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}



