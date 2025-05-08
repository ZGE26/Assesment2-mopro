package com.example.assesment2.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DialogInfo(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {Text(text = title) },
        text = {Text(text = message) },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
                Button(onClick = onConfirmation) {
                Text(text = "OK")
            }
        }
    )
}

@Preview
@Composable
fun DialogInfoPreview() {
    DialogInfo(
        title = "Title",
        message = "This is a message",
        onDismiss = {},
        onConfirmation = {}
    )
}