package com.cincinnatiai.composepipelines.repository

import com.cincinnatiai.composepipelines.api.CategoryApi
import com.cincinnatiai.composepipelines.model.Category
import com.cincinnatiai.composepipelines.model.request.CategoryFetchAllRequest
import com.cincinnatiai.composepipelines.model.response.CategoryFetchAllResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CategoryRepositoryContract {

    suspend fun fetchCategories(lastRangeKey: String? = null): CategoryFetchAllResponse
}

class CategoryRepository(
    private val clientId: String,
    private val clientKey: String,
    private val api: CategoryApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CategoryRepositoryContract {

    private val cache = hashMapOf<String, CategoryFetchAllResponse>()

    override suspend fun fetchCategories(lastRangeKey: String?): CategoryFetchAllResponse {
        if (cache.containsKey(clientId) && cache[clientId] != null) {
            return cache[clientId]!!
        }
        return withContext(dispatcher) {
            val response = api.fetchAll(
                CategoryFetchAllRequest(
                    clientId,
                    clientKey,
                    lastRangeKey
                )
            )
            cache[clientId] = response
            return@withContext response
        }
    }
}