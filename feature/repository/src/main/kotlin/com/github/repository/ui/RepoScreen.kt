package com.github.repository.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.github.designsystem.theme.MyMaterialTheme
import com.github.repository.model.UiRepo
import com.github.repository.ui.mvi.RepoIntent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RepoRoute(
    name: String,
    viewModel: RepoViewModel = koinViewModel { parametersOf(name) },
) {
    RepoScreen(
        isLoading = viewModel.viewState.isLoading, items = viewModel.viewState.repoData
    ) { branch, repo ->
        viewModel.onIntent(RepoIntent.DownloadRepo(branch, repo))
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RepoScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    isLoading: Boolean,
    items: List<UiRepo>,
    onBreachSelected: (String, UiRepo) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(

            contentColor = MaterialTheme.colorScheme.primaryContainer,
            backgroundColor = MaterialTheme.colorScheme.primary,
            title = {
                Text("Repositories")
            })
    }) {
        val context = LocalContext.current
        val intent = remember { Intent(Intent.ACTION_VIEW) }
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
            LazyColumn(
                modifier = Modifier.testTag("usersList"), contentPadding = contentPadding
            ) {
                items(items.size) { index ->
                    val item = items[index]
                    Card(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                        elevation = 4.dp
                    ) {
                        RepoItem(name = item.name, branches = item.branches, onBreachSelected = {
                            onBreachSelected(it, item)
                        }) {
                            intent.setData(Uri.parse(item.htmlUrl))
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun RepoItem(
    modifier: Modifier = Modifier,
    name: String,
    branches: List<String>,
    onBreachSelected: (String) -> Unit,
    openRepo: () -> Unit,
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current
    Card(elevation = 4.dp, modifier = modifier.onSizeChanged {
        itemHeight = with(density) { it.height.toDp() }
    }) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )

            Icon(imageVector = Icons.Filled.OpenInBrowser,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 12.dp)
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        openRepo()
                    })
            Icon(imageVector = Icons.Filled.Download,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 18.dp)
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        isContextMenuVisible = true
                    })
        }

        DropdownMenu(
            expanded = isContextMenuVisible, onDismissRequest = {
                isContextMenuVisible = false
            }, offset = DpOffset(x = (-66).dp, y = (-10).dp)
        ) {
            branches.forEach {
                DropdownMenuItem(onClick = {
                    onBreachSelected(it)
                    isContextMenuVisible = false
                }) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    MyMaterialTheme {
        Surface {
            RepoItem(name = "item.name", branches = emptyList(), onBreachSelected = { }) {}
        }
    }
}
