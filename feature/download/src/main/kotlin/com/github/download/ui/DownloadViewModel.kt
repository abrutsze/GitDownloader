package com.github.download.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.download.domain.GetDownloadedRepoUseCase
import com.github.mvi.MviBaseViewModel
import com.github.download.ui.mvi.DownloadAction
import com.github.download.ui.mvi.DownloadIntent
import com.github.download.ui.mvi.DownloadReducer
import com.github.download.ui.mvi.DownloadState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DownloadViewModel(
    val getDownloadedRepoUseCase: GetDownloadedRepoUseCase,
    downloadReducer: DownloadReducer,
) :
    MviBaseViewModel<DownloadState, DownloadAction, DownloadIntent>(downloadReducer) {


    init {
        onIntent(DownloadIntent.GetDownloadData)
    }

    override fun initState(): DownloadState = DownloadState()
    override fun handleIntent(intent: DownloadIntent) {
        when (intent) {
            is DownloadIntent.GetDownloadData -> {
                getRepo()
            }
        }
    }

    private fun getRepo() =
        getDownloadedRepoUseCase().onEach {
            onAction(DownloadAction.UpdateDownload(it))
        }.catch {
            Log.i("getPagingDataFlow", "getPagingDataFlow: ${it.message}")
        }.launchIn(viewModelScope)

}

