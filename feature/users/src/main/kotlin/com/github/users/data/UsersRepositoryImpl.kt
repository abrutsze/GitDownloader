package com.github.users.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.github.network.api.RemoteServiceApi
import com.github.users.model.UiUsers

import org.koin.core.annotation.Single
private const val DEFAULT_PAGE_SIZE = 30
@Single
class UsersRepositoryImpl (
    private val remoteServiceApi: RemoteServiceApi
) : UsersRepository {

    override fun searchUsers(query: String): Pager<Int, UiUsers> {
        return Pager(
            config = PagingConfig(
                DEFAULT_PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = DEFAULT_PAGE_SIZE,
                initialLoadSize = DEFAULT_PAGE_SIZE
            )
        ) {
            UsersDataSource.Factory().create(query, remoteServiceApi)
        }
    }


}
