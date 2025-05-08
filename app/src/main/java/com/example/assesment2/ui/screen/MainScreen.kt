package com.example.assesment2.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assesment2.R
import com.example.assesment2.model.TaskHalder
import com.example.assesment2.navigation.Screen
import com.example.assesment2.ui.component.CreateTask
import com.example.assesment2.util.SettingsDataStore
import com.example.assesment2.util.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val dataStore = SettingsDataStore(context)
    val showList by dataStore.layoutFlow.collectAsState(initial = true)

    val factory = ViewModelFactory(context)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.halder.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            var expanded by remember { mutableStateOf(false) }
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.End,
                ) {
                    AnimatedVisibility(
                        visible = expanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        FloatingActionButton(
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    dataStore.saveLayout(!showList)
                                }
                            },
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(
                                painter = painterResource(
                                    if (showList) R.drawable.baseline_grid_view_24
                                    else R.drawable.baseline_format_list_bulleted_24
                                ),
                                contentDescription = stringResource(R.string.Tampilan)
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = expanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        FloatingActionButton(
                            onClick = { showDialog = true },
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(Icons.Filled.Create, contentDescription = stringResource(R.string.tambah_task))
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
    ) { innerPadding ->
        ScreenContent(showList, Modifier.padding(innerPadding), navController, data)

        if (showDialog) {
            CreateTask(
                onDismissRequest = { showDialog = false },
                onConfirmation = { title, category ->
                    viewModel.insert(title, category)
                }
            )
        }
    }
}

@Composable
fun ScreenContent(
    showList: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    data: List<TaskHalder>
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredData = data.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
                it.category.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(stringResource(R.string.cari_task)) },
            singleLine = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_search_24),
                    contentDescription = stringResource(R.string.cari_task),
                )
            },
            shape = RoundedCornerShape(percent = 50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            )
        )

        if (filteredData.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.invalid_data))
            }
        } else {
            if (showList) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 84.dp)
                ) {
                    items(filteredData) {
                        ListTask(
                            taskHalder = it,
                            onClick = { navController.navigate(Screen.ListTask.passHalderId(it.id)) }
                        )
                        HorizontalDivider()
                    }
                }
            } else {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(8.dp, bottom = 84.dp)
                ) {
                    items(filteredData) {
                        GridItem(
                            taskHalder = it,
                            onClick = { navController.navigate(Screen.ListTask.passHalderId(it.id)) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ListTask(taskHalder: TaskHalder, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_notes_24),
            contentDescription = stringResource(R.string.avatar),
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = taskHalder.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(taskHalder.category)
        }
    }
}

@Composable
fun GridItem(taskHalder: TaskHalder, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, DividerDefaults.color)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = taskHalder.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = taskHalder.category,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(rememberNavController())
}

