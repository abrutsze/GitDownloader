package com.github.users.ui.mvi

import com.github.mvi.Reducer
import kotlinx.coroutines.flow.update
import org.koin.core.annotation.Single

@Single
class UsersReducer : Reducer<UsersAction, UsersState> {
    override fun reduce(action: UsersAction, state: UsersState): UsersState {
        return when (action) {
            is UsersAction.OnQuery ->
                state.copy(inputText = action.query, lastSearchedQuery = action.query)

            is UsersAction.UpdateUsers -> {
                state.copy(
                    pagingData = action.detailItem,
                    error = null,
                )
            }
        }
    }
}