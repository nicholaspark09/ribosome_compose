package com.cincinnatiai.composepipelines.model.response

import com.cincinnatiai.composepipelines.model.PipelineModel
import com.google.gson.annotations.SerializedName

data class PaginatedPipelineResponse(
    @SerializedName("last_range_key")
    val lastRangeKey: String,
    val results: List<PipelineModel>? = emptyList()
)