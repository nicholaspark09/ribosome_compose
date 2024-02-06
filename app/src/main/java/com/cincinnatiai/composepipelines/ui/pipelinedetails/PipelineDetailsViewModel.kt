package com.cincinnatiai.composepipelines.ui.pipelinedetails

import androidx.lifecycle.ViewModel
import com.cincinnatiai.composepipelines.di.DIModule
import com.cincinnatiai.composepipelines.model.PipelineModel
import com.cincinnatiai.composepipelines.repository.PipelineRepositoryContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PipelineDetailsViewModel : ViewModel() {
    val repo: PipelineRepositoryContract by lazy {
        DIModule.getInstance().pipelineRepository
    }

    private val _pipeline = MutableStateFlow<PipelineModel?>(null)
    val pipeline: StateFlow<PipelineModel?> = _pipeline
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun initialize() {
        _pipeline.emit(repo.selectedPipeline)
    }
}