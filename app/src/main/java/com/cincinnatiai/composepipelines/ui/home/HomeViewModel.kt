package com.cincinnatiai.composepipelines.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.cincinnatiai.composepipelines.R
import com.cincinnatiai.composepipelines.di.DIModule
import com.cincinnatiai.composepipelines.model.Category
import com.cincinnatiai.composepipelines.model.PipelineModel
import com.cincinnatiai.composepipelines.repository.CategoryRepositoryContract
import com.cincinnatiai.composepipelines.repository.PipelineRepositoryContract
import com.cincinnatiai.composepipelines.ui.navbar.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class HomeViewModel : ViewModel() {
    private val repo: CategoryRepositoryContract by lazy {
        DIModule.getInstance().repository
    }
    private val pipelineRepo: PipelineRepositoryContract by lazy {
        DIModule.getInstance().pipelineRepository
    }
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _categoryState = MutableStateFlow<HomeCategoryState>(HomeCategoryState.Loading)
    val categoryState: StateFlow<HomeCategoryState> = _categoryState
    private val _pipelines = MutableStateFlow<List<PipelineModel>>(emptyList())
    val pipelines: StateFlow<List<PipelineModel>> = _pipelines
    private val _errorState = MutableStateFlow<Int?>(null)
    val errorState: StateFlow<Int?> = _errorState
    private var lastRangeKey: String? = null

    suspend fun start() {
        updateLoading(true)
        try {
            val result = repo.fetchCategories(lastRangeKey)
            _categoryState.emit(HomeCategoryState.CategoriesLoaded(result.results))
            this.lastRangeKey = result.lastRangeKey
        } catch (e: Throwable) {
            _errorState.emit(R.string.categories_error)
        }
        updateLoading(false)
    }

    fun fetchPipelines(
        selectedCategory: Category? = null
    ) {
        Log.e("HomeScreen", "Trying to fetch api with category: " + selectedCategory?.partitionKey)
        viewModelScope.launch {
            if (selectedCategory == null) return@launch
            updateLoading(true)
            try {
                val result = pipelineRepo.fetchAll(selectedCategory.rangeKey)
                _pipelines.emit(result.results ?: emptyList())
            } catch (e: Throwable) {
                e.printStackTrace()
                _errorState.emit(R.string.pipelines_load_error)
            }
            updateLoading(false)
        }
    }

    fun navigateToPipeline(pipeline: PipelineModel, navController: NavController) {
        pipelineRepo.selectedPipeline = pipeline
        navController.navigate(Destination.PipelineDetails.route)
    }

    private fun updateLoading(isLoading: Boolean) {
        viewModelScope.launch {
            _isLoading.emit(isLoading)
        }
    }
}

sealed class HomeCategoryState {
    object Loading : HomeCategoryState()
    data class CategoriesLoaded(val categories: List<Category>) : HomeCategoryState()
}