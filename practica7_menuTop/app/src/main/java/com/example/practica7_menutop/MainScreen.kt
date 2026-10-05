package com.example.practica7_menutop

import android.graphics.drawable.shapes.RoundRectShape
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current

    var menuExpandido by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            IconButton(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.inversePrimary, CircleShape),
                onClick = {
                Toast.makeText(context, "Elemento agregado", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.Cyan),
                title = { Text("Mi aplicación") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Click en Navegación", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Añadir a Favoritos", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorito")
                    }

                    IconButton(onClick = { menuExpandido = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Más opciones")
                    }
                    DropdownMenu(
                        expanded = menuExpandido,
                        onDismissRequest = { menuExpandido = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Configuración") },
                            onClick = {
                                menuExpandido = false
                                Toast.makeText(context, "Configuración seleccionada", Toast.LENGTH_SHORT).show()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Acerca de") },
                            onClick = {
                                menuExpandido = false
                                Toast.makeText(context, "Acerca de seleccionada", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido a Material Design",
                fontSize = 22.sp,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Explora las opciones de la barra superior",
                fontSize = 16.sp
            )
        }

    }
}