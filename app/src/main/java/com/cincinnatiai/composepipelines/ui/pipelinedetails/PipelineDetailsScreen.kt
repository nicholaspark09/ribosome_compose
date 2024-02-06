package com.cincinnatiai.composepipelines.ui.pipelinedetails

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cincinnatiai.composepipelines.R
import com.cincinnatiai.composepipelines.model.PipelineModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PipelineDetailsScreen(
    navController: NavController,
    detailViewModel: PipelineDetailsViewModel = viewModel()
) {
    val pipeline by detailViewModel.pipeline.collectAsState()
    val isLoading: Boolean by detailViewModel.isLoading.collectAsState()

    LaunchedEffect(key1 = Unit) {
        detailViewModel.initialize()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                androidx.compose.material3.Text(
                    text = pipeline?.title ?: stringResource(
                        id = R.string.loading
                    ),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
            }, backgroundColor = Color.White)
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(color = Color.White)
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                showPipelineCard(pipeline = pipeline) {

                }
                showCampaignsCard(pipeline = pipeline) {

                }
            }
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun showPipelineCard(
    pipeline: PipelineModel?,
    onEditClicked: (PipelineModel) -> Unit
) {
    if (pipeline == null) return
    DetailCard(
        stringResource(id = R.string.details_title),
        titleAction = {
            onEditClicked(pipeline)
        },
        titleActionTitle = stringResource(id = R.string.edit)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = pipeline.description,
            style = MaterialTheme.typography.body1
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.pipeline_type, pipeline.type ?: "DEFAULT"),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun showCampaignsCard(
    pipeline: PipelineModel?,
    onCreateClicked: (PipelineModel) -> Unit
) {
    if (pipeline == null) return
    DetailCard(
        stringResource(id = R.string.campaign_card_title),
        titleAction = {
            onCreateClicked(pipeline)
        },
        titleActionTitle = stringResource(id = R.string.create)
    ) {

    }
}