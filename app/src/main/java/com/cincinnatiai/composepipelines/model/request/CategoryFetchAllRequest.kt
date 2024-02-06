package com.cincinnatiai.composepipelines.model.request

import com.google.gson.annotations.SerializedName

data class CategoryFetchAllRequest(
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_key")
    val clientKey: String,
    @SerializedName("last_range_key")
    val lastRangeKey: String? = null
)