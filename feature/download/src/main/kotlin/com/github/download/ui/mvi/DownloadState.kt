package com.github.download.ui.mvi

import com.github.mvi.MviState
import com.github.download.model.UiDownload

data class DownloadState(
    override val error: String? = null,
    val repoData: List<UiDownload> = emptyList(),
    val isLoading: Boolean = true
) : MviState