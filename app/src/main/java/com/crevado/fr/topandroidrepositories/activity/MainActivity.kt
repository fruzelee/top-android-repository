package com.crevado.fr.topandroidrepositories.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.crevado.fr.topandroidrepositories.pages.DetailsPage
import com.crevado.fr.topandroidrepositories.pages.RepositoryListPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunApplication(viewModel)
        }
    }

    @Composable
    private fun RunApplication(viewModel: MainViewModel) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "repository_list_page",
            builder = {
                composable(
                    "repository_list_page",
                    content = {
                        RepositoryListPage(
                            navController = navController,
                            viewModel = viewModel
                        )
                    })
                composable(
                    "details_page",
                    content = { DetailsPage(navController = navController, viewModel = viewModel) })
            })
    }
}
