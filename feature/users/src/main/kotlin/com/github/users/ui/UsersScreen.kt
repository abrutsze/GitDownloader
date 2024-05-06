package com.github.users.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.github.designsystem.theme.HintColor
import com.github.designsystem.theme.PlaceHolderColor
import com.github.feature.detail.R
import com.github.users.model.UiUsers
import com.github.users.ui.component.ErrorState
import com.github.users.ui.component.SearchBar
import com.github.users.ui.component.UserPlaceHolder
import com.github.users.ui.mvi.UsersState
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.koinViewModel

@Composable
fun UsersRoute(
    viewModel: UsersViewModel = koinViewModel(),
    openUserRepository: (String) -> Unit,
    openDownloadRepository: () -> Unit,
) {
    val state = viewModel.viewState
    UsersScreen(state, openUserRepository, onInputTextChanged = {
        viewModel.search(it)
    }, openDownloadRepository = openDownloadRepository)
}

@Composable
internal fun UsersScreen(
    viewState: UsersState,
    openUserRepository: (String) -> Unit,
    onInputTextChanged: (String) -> Unit,
    openDownloadRepository: () -> Unit,
) {

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                openDownloadRepository()
            }
        ) {
            Icon(Icons.Filled.Dataset, "")
        }
    }) { innerPadding ->
        val lazyPagingItems = viewState.pagingData?.collectAsLazyPagingItems()
        MainContent(
            modifier = Modifier.padding(innerPadding),
            inputText = viewState.inputText,
            lazyPagingItems = lazyPagingItems,
            isSearchBarHasFocus = viewState.isSearchBarFocused,
            openUserRepository = openUserRepository,
            focusSearchBar = {},
            onInputTextChanged = onInputTextChanged,
        )

    }

}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    inputText: String,
    lazyPagingItems: LazyPagingItems<UiUsers>?,
    isSearchBarHasFocus: Boolean,
    openUserRepository: (String) -> Unit,
    focusSearchBar: (Boolean) -> Unit,
    onInputTextChanged: (String) -> Unit,
) {
    val searchBarHeight = 54.dp
    val searchBarHeightWithPadding = searchBarHeight + 16.dp
    val refreshLoadState = lazyPagingItems?.loadState?.refresh

    if (lazyPagingItems?.loadState == null) {
        InitialState(
            modifier = modifier,
        )
    } else if (refreshLoadState is LoadState.Loading) {
        UserListLoadingState(modifier = modifier.padding(top = searchBarHeightWithPadding))
    } else if (refreshLoadState is LoadState.Error) {
        ErrorState(
            modifier = modifier,
            onRetry = { lazyPagingItems.retry() },
        )
    } else if (lazyPagingItems.loadState.append.endOfPaginationReached && lazyPagingItems.itemCount == 0) {
        NoUsersFoundState(modifier = modifier)
    } else {
        val lazyListState = rememberLazyListState()
        KeyboardManager(lazyListState)
        UsersList(
            modifier = modifier,
            lazyListState = lazyListState,
            contentPadding = PaddingValues(top = searchBarHeightWithPadding, bottom = 70.dp),
            items = lazyPagingItems,
            openUserRepository = openUserRepository
        )
    }

    SearchBar(
        modifier = modifier
            .padding(16.dp)
            .height(searchBarHeight),
        inputText = inputText,
        onKeyboardStateChanged = { isOpen -> if (!isOpen) focusSearchBar(false) },
        requestFocus = isSearchBarHasFocus,
        onTextChanged = onInputTextChanged
    )
}


@Composable
private fun InitialState(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_github),
            contentDescription = "initialStatePlaceHolderImage",
            colorFilter = ColorFilter.tint(androidx.compose.material.MaterialTheme.colors.surface)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun KeyboardManager(lazyListState: LazyListState) {
    val keyboardController = LocalSoftwareKeyboardController.current
    if (lazyListState.isScrollInProgress) {
        LaunchedEffect(lazyListState.isScrollInProgress) {
            keyboardController?.hide()
        }
    }
}


@Composable
private fun NoUsersFoundState(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("usersNotFoundState"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_github),
            contentDescription = "noUsersFoundPlaceHolderImage",
            colorFilter = ColorFilter.tint(androidx.compose.material.MaterialTheme.colors.surface)
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.noUsersFound),
            fontSize = 12.sp,
            color = HintColor
        )
    }
}


@Composable
private fun UsersList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    lazyListState: LazyListState = rememberLazyListState(),
    items: LazyPagingItems<UiUsers>,
    openUserRepository: (String) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.testTag("usersList"),
            state = lazyListState,
            contentPadding = contentPadding
        ) {
            items(items.itemCount) { index ->
                val item = items[index]
                if (item == null) {
                    UserPlaceHolder()
                } else {
                    Card(
                        modifier = Modifier.padding(vertical = 16.dp,  horizontal = 8.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable { openUserRepository(item.login) }
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(48.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = PlaceHolderColor),
                                painter = rememberAsyncImagePainter(item.avatarUrl),
                                contentScale = ContentScale.Crop,
                                contentDescription = "userPhoto"
                            )
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth(),
                                text = item.login,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UserListLoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("usersListPlaceHolder")
    ) {
        repeat(times = 20) {
            UserPlaceHolder()
        }
    }
}
