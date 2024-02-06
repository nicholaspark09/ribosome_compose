package com.cincinnatiai.composepipelines.model.response

import com.cincinnatiai.composepipelines.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryFetchAllResponse(
    @SerializedName("results")
    val results: List<Category>,
    @SerializedName("last_range_key")
    val lastRangeKey: String? = null
)