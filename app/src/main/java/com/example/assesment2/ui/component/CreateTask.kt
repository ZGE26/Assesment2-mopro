package com.example.assesment2.ui.component

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.assesment2.R


@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTask(
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String) -> Unit
) {
    var titleState by remember { mutableStateOf(TextFieldValue("")) }
    var categryState by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismissRequest) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(), // Ukuran layar penuh
                shape = RoundedCornerShape(24.dp), // Sudut tumpul
                tonalElevation = 8.dp
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Tambah Task",
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            },
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable { onDismissRequest() }
                                )
                            },
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary
                            ),
                            actions = {
                                IconButton(
                                    onClick = {
                                        if (titleState.text.isNotBlank() && categryState.text.isNotBlank()) {
                                            onConfirmation(titleState.text, categryState.text)
                                            onDismissRequest()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                R.string.invalid,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Confirm",
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        )
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = titleState,
                            onValueChange = { titleState = it },
                            label = { Text("Judul") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = categryState,
                            onValueChange = { categryState = it },
                            label = { Text("Kategori") },
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskJudulDialogPreview() {
    CreateTask(
        onDismissRequest = {},
        onConfirmation = { title: String, categry: String ->
            println("Task added: Title = $title, Category = $categry")
        }
    )
}
