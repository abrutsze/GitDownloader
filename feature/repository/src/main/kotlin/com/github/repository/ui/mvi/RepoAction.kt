package com.github.repository.ui.mvi

import com.github.mvi.MviAction
import com.github.repository.model.UiRepo

sealed class RepoAction : MviAction {
    data class UpdateRepo(val repoData: List<UiRepo> ) : RepoAction()
}