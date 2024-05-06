package com.github.repository.ui.mvi

import com.github.mvi.Reducer
import org.koin.core.annotation.Single

@Single
class RepoReducer : Reducer<RepoAction, RepoState> {
    override fun reduce(action: RepoAction, state: RepoState): RepoState {
        return when (action) {
            is RepoAction.UpdateRepo -> {
                state.copy(
                    repoData =action.repoData,
                    isLoading = false,
                )
            }
        }
    }
}