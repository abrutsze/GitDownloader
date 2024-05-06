package com.github.users.data

import androidx.paging.Pager
import com.github.network.entities.UserItemResponse
import com.github.users.model.UiUsers

interface UsersRepository {
    fun searchUsers(query: String): Pager<Int, UiUsers>
}
