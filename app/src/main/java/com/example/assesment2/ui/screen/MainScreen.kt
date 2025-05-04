package com.example.assesment2.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assesment2.R
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var showList by remember { mutableStateOf(true) }

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
        },
        floatingActionButton = {
            var expanded by remember { mutableStateOf(false) }

            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    if (expanded) {
                        FloatingActionButton(
                            onClick = { showList = !showList},
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(
                                painter = painterResource(
                                    if (showList) R.drawable.baseline_grid_view_24
                                    else R.drawable.baseline_format_list_bulleted_24
                                ),
                                contentDescription = "Tambah Reminder")
                        }
                        FloatingActionButton(
                            onClick = { /* Tambah Catatan */ },
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(Icons.Filled.Create, contentDescription = "Tambah Catatan")
                        }
                    }

                    FloatingActionButton(
                        onClick = { expanded = !expanded },
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.Close else Icons.Filled.Add,
                            contentDescription = "Menu Tambah",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

    ) { innerPadding->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(8.dp)
    ) {
        Text("Testing")
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}