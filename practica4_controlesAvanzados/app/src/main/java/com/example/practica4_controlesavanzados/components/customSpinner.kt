package com.example.practica4_controlesavanzados.components
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember


@Composable
fun CustomSpinner() {
    var expanded by remember { mutableStateOf( false) }
    var selectedText by remember { mutableStateOf( "Seleccionar Opción") }


    Box (modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            selectedText,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )

        DropdownMenu (
            expanded,
            onDismissRequest = { expanded = true }
        ) {
            DropdownMenuItem(
                text = { Text("Elemento A") },
                onClick = {
                    selectedText = "Elemento A"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Elemento B") },
                onClick = {
                    selectedText = "Elemento B"
                    expanded = false
                }
            )
        }

    }
}



