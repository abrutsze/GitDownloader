package com.github.user

import android.app.Application
import com.github.database.DatabaseModule
import com.github.datastore.impl.di.DataStoreModule
import com.github.users.di.UsersModule
import com.github.dispatchers.provider.di.DispatchersModule
import com.github.download.di.DownloadedModule
import com.github.network.impl.di.DataModule
import com.github.repository.di.RepoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

import org.koin.ksp.generated.*

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }
    private val modules = listOf(
        AppModule().module,
        DataModule().module,
        DispatchersModule().module,
        DataStoreModule().module,
        RepoModule().module,
        UsersModule().module,
        DatabaseModule().module,
        DownloadedModule().module,
    )
}