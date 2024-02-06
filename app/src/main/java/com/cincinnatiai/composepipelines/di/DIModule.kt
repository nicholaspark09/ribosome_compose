package com.cincinnatiai.composepipelines.di

import com.cincinnatiai.composepipelines.BuildConfig
import com.cincinnatiai.composepipelines.api.CategoryApi
import com.cincinnatiai.composepipelines.api.PipelineApi
import com.cincinnatiai.composepipelines.repository.CategoryRepository
import com.cincinnatiai.composepipelines.repository.CategoryRepositoryContract
import com.cincinnatiai.composepipelines.repository.PipelineRepository
import com.cincinnatiai.composepipelines.repository.PipelineRepositoryContract
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

enum class PFSEnvironment(val url: String, val apiKey: String) {
    dev(
        "https://x3ej1ta2md.execute-api.us-east-2.amazonaws.com/prod/",
        BuildConfig.DEV_API_KEY
    ),
    gamma(
        "https://smrc5g89af.execute-api.us-east-2.amazonaws.com/prod/",
        BuildConfig.GAMMA_API_KEY
    ),
    prod(
        "https://p9uqvaw5p5.execute-api.us-east-2.amazonaws.com/prod/",
        BuildConfig.PROD_API_KEY
    )
}

class DIModule(
    private val environment: PFSEnvironment = PFSEnvironment.dev
) {

    private val url by lazy {
        environment.url
    }

    private val clientId: String by lazy { BuildConfig.CLIENT_ID }
    private val clientKey: String by lazy { BuildConfig.CLIENT_KEY }
    private val interceptor: Interceptor by lazy {
        Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", environment.apiKey)
                .build()
            return@Interceptor chain.proceed(request)
        }
    }
    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        val level = if (environment == PFSEnvironment.prod) HttpLoggingInterceptor.Level.NONE
        else HttpLoggingInterceptor.Level.BODY
        return@lazy HttpLoggingInterceptor().apply {
            setLevel(level)
        }
    }
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(environment.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val categoryApi: CategoryApi by lazy {
        retrofit.create(CategoryApi::class.java)
    }
    private val pipelineApi: PipelineApi by lazy {
        retrofit.create(PipelineApi::class.java)
    }
    val repository: CategoryRepositoryContract by lazy {
        CategoryRepository(clientId, clientKey, categoryApi)
    }
    val pipelineRepository: PipelineRepositoryContract by lazy {
        PipelineRepository(clientId, clientKey, pipelineApi)
    }

    companion object {
        @Volatile
        private var INSTANCE: DIModule? = null

        fun init(environment: PFSEnvironment = PFSEnvironment.dev) {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = DIModule(environment)
                    }
                }
            }
        }

        fun getInstance() = INSTANCE ?: throw IllegalAccessException("Please call init first")
    }

}
