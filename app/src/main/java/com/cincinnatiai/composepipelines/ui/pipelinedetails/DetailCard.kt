package com.cincinnatiai.composepipelines.ui.pipelinedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailCard(
    title: String,
    titleAction: () -> Unit = {},
    titleActionTitle: String? = null,
    details: @Composable ColumnScope.() -> Unit = {},
) {
    Column {
        Row {
            Text(
                text = title,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp
                )
            )
            if (titleActionTitle != null) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier.padding(end = 16.dp),
                        onClick = {
                            titleAction()
                        }) {
                        androidx.compose.material.Text(titleActionTitle)
                    }
                }
            }
        }
        Column(
            content = details,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )
        Divider(
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview
@Composable
fun DetailCardPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        DetailCard(
            title = "PIPELINES"
        )
        {
            Text(text = "Description")
            Text(text = "Edit")
        }
    }
}
