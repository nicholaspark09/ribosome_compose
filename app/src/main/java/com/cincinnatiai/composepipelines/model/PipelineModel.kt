package com.cincinnatiai.composepipelines.model

import com.google.gson.annotations.SerializedName

data class PipelineModel(
    @SerializedName("id")
    val id: String ,
    @SerializedName("partition_key")
    val partitionKey: String,
    @SerializedName("range_key")
    val rangeKey: String,
    val title: String,
    val description: String,
    val created: String,
    val modified: String,
    val status: String = "ACTIVE",
    @SerializedName("is_public")
    val isPublic: Boolean,
    val auxiliarHashKey: String? = null,
    val type: String? = null
)