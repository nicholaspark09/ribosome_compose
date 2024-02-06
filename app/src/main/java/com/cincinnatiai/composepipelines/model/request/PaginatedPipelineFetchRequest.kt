package com.cincinnatiai.composepipelines.model.request

import com.google.gson.annotations.SerializedName

data class PaginatedPipelineFetchRequest(
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_key")
    val clientKey: String,
    @SerializedName("identifying_category")
    val identifyingCategory: String
)