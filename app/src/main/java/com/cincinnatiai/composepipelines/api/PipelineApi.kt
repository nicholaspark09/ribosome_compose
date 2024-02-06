package com.cincinnatiai.composepipelines.api

import com.cincinnatiai.composepipelines.model.request.PaginatedPipelineFetchRequest
import com.cincinnatiai.composepipelines.model.response.PaginatedPipelineResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PipelineApi {

    @POST("pipelinefacade?controller=pipelines&action=fetchAll")
    suspend fun fetch(@Body request: PaginatedPipelineFetchRequest): PaginatedPipelineResponse
}