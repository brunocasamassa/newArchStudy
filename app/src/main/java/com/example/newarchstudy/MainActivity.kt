package com.example.newarchstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newarchstudy.ui.presentation.latest.LatestNewsScreen
import com.example.newarchstudy.ui.presentation.search.SearchNewsScreen
import com.example.newarchstudy.ui.presentation.search.SearchTopBar
import com.example.newarchstudy.ui.theme.NewArchStudyTheme
import com.example.newarchstudy.viewmodels.SearchNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.internal.TestSingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            //todo set navigation route
                NewArchStudyTheme {
                        AppNavGraph()
                }
            }


    }


    @Composable
    fun AppNavGraph(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController()
    ) {

        var homeSelected = rememberSaveable {
            mutableStateOf(true)
        }

        NavHost(navController = rememberNavController(), startDestination = "main") {
            composable("main") {


                Scaffold(
                    topBar = { if (homeSelected.value.not()) SearchTopBar() },
                    bottomBar = { HomeBottomBar(homeSelected) }) {

                        innerPadding ->
                    Modifier.padding(innerPadding)


                    AnimatedContent(
                        modifier = Modifier.padding(innerPadding),
                        targetState = homeSelected.value
                    ) {
                        when (it) {
                            true ->
                                LatestNewsScreen()

                            else ->
                                SearchNewsScreen()
                        }
                    }


                }

            }
        }




    }

    @Composable
    fun HomeBottomBar(homeSelected: MutableState<Boolean>) {
        NavigationBar(
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_home)
                    )
                },
                selected = homeSelected.value,
                onClick = { homeSelected.value = true }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_profile)
                    )
                },
                selected = homeSelected.value.not(),
                onClick = { homeSelected.value = false }
            )
        }


    }




}

