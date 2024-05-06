package com.github.repository.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.mvi.MviBaseViewModel
import com.github.network.api.Downloader
import com.github.repository.domain.RepoUseCase
import com.github.repository.domain.SaveDownloadedRepoUseCase
import com.github.repository.model.UiRepo
import com.github.repository.ui.mvi.RepoAction
import com.github.repository.ui.mvi.RepoIntent
import com.github.repository.ui.mvi.RepoReducer
import com.github.repository.ui.mvi.RepoState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RepoViewModel(
    val name: String,
    val repoUseCase: RepoUseCase,
    val saveDownloadedRepoUseCase: SaveDownloadedRepoUseCase,
    repoReducer: RepoReducer,
    private val downloader: Downloader
) :
    MviBaseViewModel<RepoState, RepoAction, RepoIntent>(repoReducer) {

    init {
        onIntent(RepoIntent.GetRepoData(name))
    }

    override fun initState(): RepoState = RepoState()
    override fun handleIntent(intent: RepoIntent) {
        when (intent) {
            is RepoIntent.GetRepoData -> {
                getRepo(intent.name)
            }

            is RepoIntent.DownloadRepo -> {
                saveDownloadBranch( intent.branchName, intent.repo)
                downloader.downloadFile(
                    intent.branchName,
                    intent.repo.fullName
                )
            }
        }
    }

    private fun getRepo(name: String) =
        repoUseCase(name).onEach {
            onAction(RepoAction.UpdateRepo(it))
        }.catch {
            Log.i("getPagingDataFlow", "getPagingDataFlow: ${it.message}")
        }.launchIn(viewModelScope)

    private fun saveDownloadBranch(branch: String, repo: UiRepo) =
        saveDownloadedRepoUseCase(branch,repo).catch {
            Log.i("getPagingDataFlow", "saveDownloadBranch: ${it.message}")
        }.launchIn(viewModelScope)
}

