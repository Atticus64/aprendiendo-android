package com.example.practica4_controlesavanzados.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


@Composable
fun CustomDatePicker() {
    var openDialog by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    var selectedDateText by remember { mutableStateOf("Seleccionar Fecha") }

    Button(
        onClick = { openDialog = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(selectedDateText)
    }

    if (openDialog) {
        DatePickerDialog (
            onDismissRequest = { openDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { milis ->
                        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        format.timeZone = TimeZone.getTimeZone("UTC")
                        selectedDateText = format.format(Date(milis))
                    }
                    openDialog = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false}) {
                    Text("Cancelar")
                }
            }

        ) {
            DatePicker(state = datePickerState)
        }
    }


}



