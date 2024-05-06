package com.github.users.ui.mvi

import com.github.users.model.UiUsers
import com.github.mvi.MviIntent

sealed class UsersIntent : MviIntent {
    data class OnUserSearch(val query:String) : UsersIntent()
}