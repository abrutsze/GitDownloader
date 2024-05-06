package com.github.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

const val userRoute = "user_route"

const val repoRoute = "repo_route" + "/{name}"
const val downloadRoute = "download_route"

@Composable
fun RootNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = userRoute,
) {
    val rootNavController: NavHostController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        usersScreen(
            openUserRepository = {
                rootNavController.navigate("repo_route/$it")
            },
            openDownloadRepository = {
                rootNavController.navigate(downloadRoute)
            }
        )

        repositoryScreen()
        downloadScreen()
    }
}
