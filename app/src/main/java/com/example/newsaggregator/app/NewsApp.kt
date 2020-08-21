package com.example.newsaggregator.app

import android.app.Application
import com.example.newsaggregator.model.*
import com.example.newsaggregator.model.db.newsDatabaseHelper
import com.example.newsaggregator.model.db.newsDatabaseModule
import com.example.newsaggregator.model.remote.connectivityModule
import com.example.newsaggregator.model.remote.networkModule
import com.example.newsaggregator.model.remote.remoteDataModel
import com.example.newsaggregator.viewmodel.mainViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NewsApp)
            modules(
                listOf(
                    centralNewsRepository,
                    networkModule,
                    newsDatabaseModule,
                    mainViewModelModule,
                    newsDatabaseHelper,
                    remoteDataModel,
                    connectivityModule
                )
            )
        }

    }
}