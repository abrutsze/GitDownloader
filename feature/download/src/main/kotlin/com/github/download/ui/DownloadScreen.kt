package com.github.download.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.github.designsystem.theme.HintColor
import com.github.designsystem.theme.PlaceHolderColor
import com.github.download.model.UiDownload
import com.github.feature.auth.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun DownloadRoute(
    viewModel: DownloadViewModel = koinViewModel(),
) {
    DownloadScreen(
        isLoading = viewModel.viewState.isLoading,
        items = viewModel.viewState.repoData
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DownloadScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    isLoading: Boolean,
    items: List<UiDownload>
) {
    Scaffold(
        topBar = {
            TopAppBar(

                contentColor = MaterialTheme.colorScheme.primaryContainer,
                backgroundColor = MaterialTheme.colorScheme.primary,
                title = {
                    Text("Downloaded branches")
                }
            )
        }) {
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

        }
        Box(modifier = modifier.fillMaxSize()) {
            if (items.isEmpty()) {
                NoFoundState()
            } else {
                LazyColumn(
                    modifier = Modifier.testTag("usersList"), contentPadding = contentPadding
                ) {
                    items(items.size) { index ->
                        val item = items[index]
                        Card(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            ),
                            elevation = 4.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp, horizontal = 16.dp),

                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = PlaceHolderColor),
                                    painter = rememberAsyncImagePainter(item.ownerAvatarUrl),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "userPhoto"
                                )

                                Column(
                                    modifier = Modifier
                                        .height(64.dp)
                                        .fillMaxWidth(),
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp),
                                            text = stringResource(R.string.user),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )

                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp),
                                            text = item.ownerLogin,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp),
                                            text = stringResource(R.string.repository),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )

                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp),
                                            text = item.name,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp),
                                            text = stringResource(R.string.branch),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )

                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp),
                                            text = item.branches,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                    }


                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NoFoundState(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.noDownloadFound),
            fontSize = 12.sp,
            color = HintColor
        )
    }
}
