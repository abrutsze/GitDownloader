package com.github.users.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.users.ui.mvi.UsersAction
import com.github.users.ui.mvi.UsersIntent
import com.github.users.ui.mvi.UsersReducer
import com.github.users.ui.mvi.UsersState
import com.github.mvi.MviBaseViewModel
import com.github.users.domain.UsersUseCase
import com.github.users.model.UiUsers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UsersViewModel(
    private val userUseCase: UsersUseCase,
    reducer: UsersReducer
) :
    MviBaseViewModel<UsersState, UsersAction, UsersIntent>(reducer) {

    override fun initState(): UsersState = UsersState()

    override fun handleIntent(intent: UsersIntent) {
        when (intent) {
            is UsersIntent.OnUserSearch -> {
                getPagingDataFlow(intent.query)
                onAction(UsersAction.OnQuery(intent.query))
            }
        }
    }

    fun search(query: String) {
        onIntent(UsersIntent.OnUserSearch(query))
    }

    private fun getPagingDataFlow(searchQuery: String) {
        val userList = userUseCase(searchQuery).cachedIn(viewModelScope)
        onAction(UsersAction.UpdateUsers(userList))
    }

}
