package com.github.datastore.impl

import com.github.datastore.api.DataStoreRepository
import org.koin.core.annotation.Single

@Single
class DataStoreRepositoryImpl(private val dataStoreService: DataStoreService) :
    DataStoreRepository {
    override var token: String
        get() = dataStoreService.token
        set(value) {
            dataStoreService.token = value
        }
}