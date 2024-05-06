package com.github.download.ui.mvi

import com.github.mvi.MviIntent

sealed class DownloadIntent : MviIntent {
    data object GetDownloadData : DownloadIntent()
}