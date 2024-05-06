package com.github.download.ui.mvi

import com.github.mvi.Reducer
import org.koin.core.annotation.Single

@Single
class DownloadReducer : Reducer<DownloadAction, DownloadState> {
    override fun reduce(action: DownloadAction, state: DownloadState): DownloadState {
        return when (action) {
            is DownloadAction.UpdateDownload -> {
                state.copy(
                    repoData =action.repoData,
                    isLoading = false,
                )
            }
        }
    }
}