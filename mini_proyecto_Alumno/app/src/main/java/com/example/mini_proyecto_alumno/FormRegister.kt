package com.example.mini_proyecto_alumno

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini_proyecto_alumno.components.CustomSwitch
import com.example.mini_proyecto_alumno.components.Drop
import com.example.practica4_controlesavanzados.components.CustomRadioButton

@Composable
fun FormRegister() {
    val context = LocalContext.current
    val profileManager = remember { ProfileManager(context) }

    val matutino = "Matutino"
    val vespertino = "Vespertino"

    var matricula by remember { mutableStateOf("") }
    var nombreCompleto by remember { mutableStateOf("") }
    var turno by remember { mutableStateOf("") }
    var carreras = arrayOf(
        "Ingeniería de Sofware",
        "Ingeniería en Procesos Industriales",
        "Ingeniería Civil",
        "Ingeniería en Nanotecnología",
    )
    var activo by remember { mutableStateOf(false) }
    var carrera by remember { mutableStateOf( "Seleccionar Opción") }

    LaunchedEffect(Unit) {
        matricula = profileManager.getMatricula()
        nombreCompleto = profileManager.getNombre()
        turno = profileManager.getTurno()
        carrera = profileManager.getCarrera()
        activo = profileManager.getStatus()
    }

    Row() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Miniproyecto: Formulario Estudiantes",
                fontSize = 22.sp,
                style = MaterialTheme.typography.headlineMedium
            )

            HorizontalDivider()

            OutlinedTextField(
                value = nombreCompleto,
                onValueChange = { nombreCompleto = it },
                label = { Text("Nombre completo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = matricula,
                onValueChange = { matricula = it },
                label = { Text("Matricula") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider()

            Drop(carreras, carrera, {
                carrera = it
            })

            CustomRadioButton(
                matutino,
                vespertino
                ,
                turno,
                {
                   turno = matutino
                },
                {
                    turno = vespertino
                }
            )

            CustomSwitch("Activo", activo, {
                activo = it
            })


            fun saveProfile() {
                profileManager.saveProfile(
                    matricula,
                    nombreCompleto,
                    carrera,
                    turno,
                    activo
                )
                Toast.makeText(context, "Configuración Guardada", Toast.LENGTH_SHORT).show()
            }

            Button(
                { saveProfile() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Perfil")
            }


            fun cargarPerfil() {
                matricula = profileManager.getMatricula()
                nombreCompleto = profileManager.getNombre()
                turno = profileManager.getTurno()
                carrera = profileManager.getCarrera()
                activo = profileManager.getStatus()
                Toast.makeText(context, "Preferencias cargadas", Toast.LENGTH_SHORT).show()
            }

            OutlinedButton(
                onClick = { cargarPerfil() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Recargar Perfil Guardado")
            }

            fun eliminarPerfil() {
                matricula = ""
                nombreCompleto = ""
                turno = ""
                carrera = ""
                activo = false
                Toast.makeText(context, "Perfil eliminado", Toast.LENGTH_SHORT).show()
            }

            OutlinedButton(
                onClick = { eliminarPerfil() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar Perfil")
            }


            Button(
                onClick = {
                    val intent = Intent(context, ConfiguractionActivity::class.java).apply {
                        putExtra("EXTRA_MATRICULA", matricula)
                        putExtra("EXTRA_NOMBRE", nombreCompleto)
                        putExtra("EXTRA_TURNO", turno)
                        putExtra("EXTRA_CARRERA", carrera)
                        putExtra("EXTRA_ACTIVO", activo)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver detalle de perfil")
            }



        }
    }

}
