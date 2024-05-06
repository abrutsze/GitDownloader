package com.github.database

import android.app.Application
import androidx.room.Room
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.github.database")
class DatabaseModule {

    @Single
    fun provideDataBase(application: Application): GuthubDatabase =
        Room.databaseBuilder(
            application,
            GuthubDatabase::class.java,
            "db_github"
        ).
        fallbackToDestructiveMigration().build()

    @Single
    fun provideDownloadDao(postDataBase: GuthubDatabase): DownloadDao = postDataBase.getDownloadDao()

}
