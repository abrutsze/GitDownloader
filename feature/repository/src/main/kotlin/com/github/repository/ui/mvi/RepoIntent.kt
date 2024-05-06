package com.github.repository.ui.mvi

import com.github.mvi.MviIntent
import com.github.repository.model.UiRepo

sealed class RepoIntent : MviIntent {
    data class GetRepoData(val name:String) : RepoIntent()
    data class DownloadRepo(val branchName: String, val repo: UiRepo) : RepoIntent()
}