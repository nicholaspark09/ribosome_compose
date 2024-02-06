package com.cincinnatiai.composepipelines.repository

import android.util.Log
import com.cincinnatiai.composepipelines.api.PipelineApi
import com.cincinnatiai.composepipelines.model.PipelineModel
import com.cincinnatiai.composepipelines.model.request.PaginatedPipelineFetchRequest
import com.cincinnatiai.composepipelines.model.response.PaginatedPipelineResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PipelineRepositoryContract {

    var selectedPipeline: PipelineModel?

    suspend fun fetchAll(categoryRangeKey: String, lastRangeKey: String? = null): PaginatedPipelineResponse
}

class PipelineRepository(
    private val clientId: String,
    private val clientKey: String,
    private val api: PipelineApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PipelineRepositoryContract {

    override var selectedPipeline: PipelineModel? = null

    override suspend fun fetchAll(categoryRangeKey: String, lastRangeKey: String?) = withContext(dispatcher) {
            return@withContext api.fetch(PaginatedPipelineFetchRequest(
                clientId,
                clientKey,
                categoryRangeKey
            ))
        }
}