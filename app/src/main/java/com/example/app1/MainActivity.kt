package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlertDialogApp()
        }
    }
}

@Composable
fun AlertDialogApp() {
    var confirmationText by remember { mutableStateOf("Esperando acción...") }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var showDeletionDialog by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }
    var showAuthenticationDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = confirmationText,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Button(
            onClick = { showConfirmationDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(bottom = 40.dp)
        ) {
            Text("Confirmación de Acción", fontSize = 20.sp)
        }

        Button(
            onClick = { showDeletionDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(bottom = 40.dp)
        ) {
            Text("Eliminar Elemento", fontSize = 20.sp)
        }

        Button(
            onClick = { showInfoDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(bottom = 40.dp)
        ) {
            Text("Aviso Importante", fontSize = 20.sp)
        }

        Button(
            onClick = { showAuthenticationDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(bottom = 40.dp)
        ) {
            Text("Requiere Autenticación", fontSize = 20.sp)
        }

        Button(
            onClick = { showErrorDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(bottom = 40.dp)
        ) {
            Text("Error Crítico", fontSize = 20.sp)
        }


        if (showConfirmationDialog) {
            ConfirmationDialog(
                onConfirm = {
                    confirmationText = "Acción confirmada"
                    showConfirmationDialog = false
                },
                onDismiss = { showConfirmationDialog = false }
            )
        }

        if (showDeletionDialog) {
            DeletionDialog(
                onDelete = {
                    confirmationText = "Elemento eliminado"
                    showDeletionDialog = false
                },
                onDismiss = { showDeletionDialog = false }
            )
        }

        if (showInfoDialog) {
            InfoDialog(
                onDismiss = { showInfoDialog = false }
            )
        }

        if (showAuthenticationDialog) {
            AuthenticationDialog(
                onAuthenticate = {
                    confirmationText = "Usuario autenticado"
                    showAuthenticationDialog = false
                },
                onDismiss = { showAuthenticationDialog = false }
            )
        }

        if (showErrorDialog) {
            ErrorDialog(
                onRetry = {
                    confirmationText = "Intento de reintento"
                    showErrorDialog = false
                },
                onDismiss = { showErrorDialog = false }
            )
        }
    }
}

@Composable
fun ConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Confirmación de Acción", fontSize = 28.sp)
        },
        text = {
            Text(
                "¿Estás seguro de que deseas continuar con esta acción?",
                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp)
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Sí", fontSize = 20.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No", fontSize = 20.sp)
            }
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    )
}

@Composable
fun DeletionDialog(onDelete: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Eliminar Elemento", fontSize = 28.sp)
        },
        text = {
            Text(
                "Esta acción es irreversible. ¿Deseas eliminar este elemento?",
                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp)
            )
        },
        confirmButton = {
            TextButton(onClick = onDelete) {
                Text("Eliminar", fontSize = 20.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", fontSize = 20.sp)
            }
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    )
}

@Composable
fun InfoDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Aviso Importante", fontSize = 28.sp)
        },
        text = {
            Text(
                "Recuerda que los cambios realizados no se pueden deshacer.",
                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp)
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Entendido", fontSize = 20.sp)
            }
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    )
}

@Composable
fun AuthenticationDialog(onAuthenticate: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Requiere Autenticación", fontSize = 28.sp)
        },
        text = {
            Text(
                "Para continuar, necesitas autenticarte de nuevo.",
                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp)
            )
        },
        confirmButton = {
            TextButton(onClick = onAuthenticate) {
                Text("Autenticar", fontSize = 20.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", fontSize = 20.sp)
            }
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    )
}

@Composable
fun ErrorDialog(onRetry: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Error Crítico", fontSize = 28.sp)
        },
        text = {
            Text(
                "Se ha producido un error crítico. ¿Deseas intentar nuevamente?",
                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp)
            )
        },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text("Reintentar", fontSize = 20.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", fontSize = 20.sp)
            }
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlertDialogApp()
}

