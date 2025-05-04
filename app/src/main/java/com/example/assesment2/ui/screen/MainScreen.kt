package com.example.assesment2.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assesment2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                colors =TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary

                )
            )
        }
    ) { innerPadding->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    Text(
        text = stringResource(R.string.app_name),
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(rememberNavController())
}