package com.github.download.ui.mvi

import com.github.mvi.MviAction
import com.github.download.model.UiDownload

sealed class DownloadAction : MviAction {
    data class UpdateDownload(val repoData: List<UiDownload> ) : DownloadAction()
}