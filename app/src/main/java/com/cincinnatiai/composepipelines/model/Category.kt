package com.cincinnatiai.composepipelines.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("partition_key")
    val partitionKey: String,
    @SerializedName("range_key")
    val rangeKey: String,
    val title: String,
    val description: String,
    val created: String,
    val modified: String,
    val status: String
) : Serializable