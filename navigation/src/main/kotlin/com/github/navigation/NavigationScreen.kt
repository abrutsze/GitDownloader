package com.github.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.download.ui.DownloadRoute
import com.github.repository.ui.RepoRoute
import com.github.users.ui.UsersRoute

fun NavGraphBuilder.usersScreen(
    openUserRepository: (String) -> Unit,
    openDownloadRepository: () -> Unit,
) {
    composable(route = userRoute) {
        UsersRoute(
            openUserRepository = openUserRepository,
            openDownloadRepository = openDownloadRepository
        )
    }
}

fun NavGraphBuilder.repositoryScreen() {
    composable(route = repoRoute, arguments = listOf(navArgument(name = "name") {
        type = NavType.StringType
    })) {
        val name = it.arguments?.getString("name").orEmpty()
        RepoRoute(name)
    }
}

fun NavGraphBuilder.downloadScreen() {
    composable(route = downloadRoute) {
        DownloadRoute()
    }
}

