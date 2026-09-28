package com.example.mini_proyecto_alumno.components

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
fun Drop(options: Array<String>, selectedText: String, changeOption: (String) -> Unit) {
    var expanded by remember { mutableStateOf( false) }


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

            for (option in options) {
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        changeOption(option)
                        expanded = false
                    }
                )
            }
        }

    }
}



