package com.cincinnatiai.composepipelines.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cincinnatiai.composepipelines.model.PipelineModel

@Composable
fun PipelineRow(
    tag: String,
    item: PipelineModel,
    onItemClick: (PipelineModel) -> Unit) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(70.dp)
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .clickable { onItemClick(item) }
            .semantics {
                contentDescription = item.title
            }
            .testTag(tag)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = item.title, style = TextStyle(fontSize = 14.sp))
            Text(text = item.description, style = TextStyle(fontSize = 12.sp))
        }
    }
}