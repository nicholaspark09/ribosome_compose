package com.cincinnatiai.composepipelines

import android.app.Application
import com.cincinnatiai.composepipelines.di.DIModule
import com.cincinnatiai.composepipelines.di.PFSEnvironment

class ComposePipelinesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DIModule.init(PFSEnvironment.dev)
    }
}