package com.example.miniproyecto2.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miniproyecto2.data.Tarea
import com.example.miniproyecto2.data.TareaDao
import com.example.miniproyecto2.domain.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

enum class Filter {
    Completed, Pending, Normal
}

fun TextoFiltro(filtro: Filter): String {

    if (filtro == Filter.Completed)
        return "Completadas"
    if (filtro == Filter.Pending)
        return "Pendientes"

    return "Todas"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current

    var menuExpandido by remember { mutableStateOf(false) }
    var descripcionTarea by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("") }
    var fechaTarea by remember { mutableStateOf("") }

    var filtro by remember { mutableStateOf(Filter.Normal) }
    var openDialog by remember { mutableStateOf(false) }

    var opcionesPrioridad = arrayOf(
        "Urgente",
        "Importante",
        "Personal",
        "Secundaria",
    )
    val datePickerState = rememberDatePickerState()
    val coroutineScope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.tareaDao()

    val tareas by remember(filtro) {
        var lista = dao.obtenerTodos()
        if (filtro == Filter.Normal) {
            lista = dao.obtenerTodos()
        } else if (filtro == Filter.Completed ){
            lista = dao.obtenerCompletados()
        } else if (filtro == Filter.Pending ) {
            lista = dao.obtenerPendientes()
        }

        lista
    }.collectAsState(initial = emptyList())


    Scaffold(
        floatingActionButton = {
            IconButton(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.inversePrimary,
                    CircleShape
                ),
                onClick = {
                    Toast.makeText(context, "Agregar Tarea", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar una nueva tarea")
            }
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.Cyan),
                title = { Text("Tareas Admin") },
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
                        filtro = Filter.Normal
                        Toast.makeText(context, "Mostrar todas", Toast.LENGTH_SHORT).show()

                    }) {
                        Icon(imageVector = Icons.Default.Task, contentDescription = "Completadas")
                    }
                    IconButton(onClick = {
                        filtro = Filter.Completed
                        Toast.makeText(context, "Mostrar Completadas", Toast.LENGTH_SHORT).show()

                    }) {
                        Icon(imageVector = Icons.Default.CheckBox, contentDescription = "Completadas")
                    }
                    IconButton(onClick = {
                        filtro = Filter.Pending
                        Toast.makeText(context, "Mostrar Pendientes", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.Work, contentDescription = "Pendientes")
                    }
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Borrar completadas", Toast.LENGTH_SHORT).show()
                        }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "borrar completadas")
                    }

                    IconButton(onClick = { menuExpandido = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Más opciones")
                    }

                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ) {


            Text("Crear Tarea: ", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = descripcionTarea,
                onValueChange = { descripcionTarea = it },
                label = { Text("Descripción de la Tarea") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


            PrioridadSelector(opcionesPrioridad, prioridad, {
                prioridad = it
            })

            Button (
                onClick = {
                    val nuevaTarea = Tarea(id = 0, descripcionTarea, prioridad, false)
                    coroutineScope.launch(Dispatchers.IO) {
                        dao.insertar(nuevaTarea)
                    }
                },
                Modifier.fillMaxWidth()
            ) {
                Text("Guardar Tarea")
            }

            Text("Lista de Tareas <${TextoFiltro(filtro)}>", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tareas) { tarea ->
                    Card (modifier = Modifier.fillMaxWidth()) {
                        Row() {
                            Text(
                                text = "ID: ${tarea.id} | Nombre: ${tarea.descripcion} | Prioridad: ${tarea.prioridad}",
                                modifier = Modifier.padding(16.dp)
                            )
                            CompletadaCheck(
                                onChecked = {

                                    coroutineScope.launch(Dispatchers.IO) {
                                        tarea.completada = !tarea.completada
                                        dao.actualizar(tarea)
                                    }
                                },
                                isChecked = tarea.completada
                            )

                        }
                    }

                }
            }
        }

    }
}