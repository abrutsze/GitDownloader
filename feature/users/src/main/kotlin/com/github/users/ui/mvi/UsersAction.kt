package com.github.users.ui.mvi

import androidx.paging.PagingData
import com.github.users.model.UiUsers
import com.github.mvi.MviAction
import kotlinx.coroutines.flow.Flow

sealed class UsersAction : MviAction {
    data class OnQuery(val query: String) : UsersAction()
    data class UpdateUsers(val detailItem: Flow<PagingData<UiUsers>>) : UsersAction()
}