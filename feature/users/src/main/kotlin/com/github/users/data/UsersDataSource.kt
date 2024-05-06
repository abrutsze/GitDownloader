package com.github.users.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.network.api.RemoteServiceApi
import com.github.network.entities.GitHubResponse
import com.github.network.entities.UserItemResponse
import com.github.users.model.UiUsers
import com.github.users.model.toUiUsers

internal class UsersDataSource(val query: String, private val remoteServiceApi: RemoteServiceApi) :
    PagingSource<Int, UiUsers>() {
    override fun getRefreshKey(state: PagingState<Int, UiUsers>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UiUsers> {
        Log.i("getPagingDataFlow", "getPagingDataFlow: ${params.key}")
        try {
            val nextPage = params.key ?: 1
            val users = if(query.isNotEmpty())remoteServiceApi.getSearchUsers(query, 30, nextPage) else GitHubResponse<UserItemResponse>(
                emptyList()
            )
            val list = users.items.map {
                it.toUiUsers()
            }

            return LoadResult.Page(
                data = list,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (list.isEmpty()) null else nextPage.plus(1)
            )
        } catch (t: Throwable) {
            return LoadResult.Error(t)
        }
    }

    class Factory {
        fun create(query: String, remoteServiceApi: RemoteServiceApi) = UsersDataSource(query, remoteServiceApi)
    }
}
