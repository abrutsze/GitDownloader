package com.github.users.ui.mvi

import androidx.paging.PagingData
import com.github.users.model.UiUsers

import com.github.mvi.MviState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class UsersState(
    override val error: String? = null,
    val inputText: String = "",
    val lastSearchedQuery: String = "",
    val pagingData: Flow<PagingData<UiUsers>>? = null,
    val isSearchBarFocused: Boolean = false
) : MviState