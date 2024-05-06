package com.github.repository.ui.mvi

import com.github.mvi.MviState
import com.github.repository.model.UiRepo

data class RepoState(
    override val error: String? = null,
    val repoData: List<UiRepo> = emptyList(),
    val isLoading: Boolean = true
) : MviState