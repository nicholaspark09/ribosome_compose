package com.cincinnatiai.composepipelines.api

import com.cincinnatiai.composepipelines.model.request.CategoryFetchAllRequest
import com.cincinnatiai.composepipelines.model.response.CategoryFetchAllResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CategoryApi {
    @POST("pipelinefacade?controller=category&action=fetchAll")
    suspend fun fetchAll(@Body fetchAllRequest: CategoryFetchAllRequest): CategoryFetchAllResponse
}