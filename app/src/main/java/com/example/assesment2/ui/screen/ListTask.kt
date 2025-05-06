package com.example.assesment2.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assesment2.R
import com.example.assesment2.model.TaskList
import com.example.assesment2.util.ViewModelFactory

const val KEY_ID_TASK = "taskId"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTask(navController: NavHostController, id: Long) {
    val selectedTask = remember { mutableStateOf<TaskList?>(null) }
    val newTaskDialogVisible = remember { mutableStateOf(false) }
    val newTaskData = remember {
        mutableStateOf(TaskList(id = 0, title = "", description = "", date = "", halderId = id))
    }
    var title by remember { mutableStateOf("") }

    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: ListViewModel = viewModel(factory = factory)
    val taskList by viewModel.listTaskList.collectAsState()
    val viewModel2: MainViewModel = viewModel(factory = factory)

    LaunchedEffect(Unit) {
        if(id == 0L) return@LaunchedEffect
        val halder = viewModel2.getHalder(id)?: return@LaunchedEffect
        title = halder.title
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(title)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    DeleteActionList {
                        viewModel.deleteAll(id)
                        viewModel2.delete(id)
                        navController.popBackStack()
                    }
                }

            )
        }
    ) { paddingValues ->
        ListTaskContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            id = id,
            onTaskSelected = { selectedTask.value = it },
            onAddTaskClick = {
                newTaskData.value = TaskList(id = 0, title = "", description = "", date = "", halderId = id)
                newTaskDialogVisible.value = true
            },
            taskList = taskList
        )

        selectedTask.value?.let { task ->
            var tempTask by remember { mutableStateOf(task) }

            DetailList(
                task = tempTask,
                onDismiss = { selectedTask.value = null },
                onTitleChange = { tempTask = tempTask.copy(title = it) },
                onDescriptionChange = { tempTask = tempTask.copy(description = it) },
                onDateChange = { tempTask = tempTask.copy(date = it) },
                onClick = {
                    viewModel.update(tempTask)
                    selectedTask.value = null
                },
                viewModel = viewModel
            )
        }


        if (newTaskDialogVisible.value) {
            DetailList(
                task = newTaskData.value,
                onDismiss = { newTaskDialogVisible.value = false },
                onTitleChange = { newTaskData.value = newTaskData.value.copy(title = it) },
                onDescriptionChange = { newTaskData.value = newTaskData.value.copy(description = it) },
                onDateChange = { newTaskData.value = newTaskData.value.copy(date = it) },
                onClick = {
                    if (newTaskData.value.title.isNotBlank() && newTaskData.value.description.isNotBlank() && newTaskData.value.date.isNotBlank()) {
                        viewModel.insert(
                            newTaskData.value.title,
                            newTaskData.value.description,
                            newTaskData.value.date,
                            newTaskData.value.halderId
                        )
                        newTaskDialogVisible.value = false
                    }
                },
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun ListTaskContent(
    modifier: Modifier,
    id: Long,
    onTaskSelected: (TaskList) -> Unit,
    onAddTaskClick: () -> Unit,
    taskList: List<TaskList>
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val filteredTasks = taskList.filter { it.halderId == id }

        if (filteredTasks.isEmpty()) {
            Text(
                text = "List belum ada.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            filteredTasks.forEach { task ->
                GridItem(taskList = task, onClick = { onTaskSelected(task) })
            }
        }

        TextButton(
            onClick = onAddTaskClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = MaterialTheme.shapes.large,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        ) {
            Text(
                text = "Tambah Task",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
fun GridItem(taskList: TaskList, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
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
                text = taskList.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = taskList.description,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Text(taskList.date)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailList(
    task: TaskList,
    onDismiss: () -> Unit,
    onTitleChange: (String) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {},
    onDateChange: (String) -> Unit = {},
    onClick: () -> Unit = {},
    viewModel: ListViewModel
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp,
        ) {
            var isTitleError by remember { mutableStateOf(false) }
            var isDescriptionError by remember { mutableStateOf(false) }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Detail Task") },
                        navigationIcon = {
                            IconButton(onClick = onDismiss) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                isTitleError = task.title.isBlank()
                                isDescriptionError = task.description.isBlank()

                                if (!isTitleError && !isDescriptionError) {
                                    onClick()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Save",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                            if(task.id != 0L) {
                                DeleteActionList {
                                    viewModel.deleteList(task.id)
                                    onDismiss()
                                }
                            }
                        },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary
                        ),
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Detail Task",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = task.title,
                        onValueChange = {
                            isTitleError = false
                            onTitleChange(it)
                        },
                        label = { Text("Judul") },
                        isError = isTitleError,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = task.description,
                        onValueChange = {
                            isDescriptionError = false
                            onDescriptionChange(it)
                        },
                        label = { Text("Deskripsi") },
                        isError = isDescriptionError,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = task.date,
                        onValueChange = { onDateChange(it) },
                        label = { Text("Tanggal") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun DeleteActionList(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = {expanded = true}) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.hapus),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = {
                    Text(stringResource(R.string.hapus))
                },
                onClick = {
                    delete()
                    expanded = false
                },
            )
        }
    }
}

@Preview
@Composable
fun ListTaskPreview() {
    ListTask(rememberNavController(), 1L)
}
