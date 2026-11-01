package com.example.miniproyecto3.presentation

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.miniproyecto3.data.Publication
import com.example.miniproyecto3.domain.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun saveToGallery(context: Context, bitmap: Bitmap): Uri? {
    val values = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "img_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/app")
    }
    val uri = context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
    ) ?: return null
    context.contentResolver.openOutputStream(uri)?.use {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it)
    }

    return uri
}


@Composable
fun AltaScreen() {
    val context = LocalContext.current

    val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    var openDialog by remember { mutableStateOf(false) }
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.publicationDao()

    val datePickerState = rememberDatePickerState()
    var selectedDateText by remember { mutableStateOf("seleccionar fecha") }

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                context.contentResolver.takePersistableUriPermission(uri, flags)
                url = uri.toString()
            } else {
                Toast.makeText(context, "Fallo al imagen tomar de la galeria", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    val camaraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            val result = saveToGallery(context, bitmap)
            if (result != null) {
//                context.contentResolver.takePersistableUriPermission(result)
                url = result.toString()
            }
        }

    }

    val permisoCamaraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (concedido) {
            camaraLauncher.launch(null)
        } else {
            Toast.makeText(
                context,
                "Se necesita permiso para usar la cámara",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun tomarFoto() {
        val permisoConcedido = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        if (permisoConcedido) {
            camaraLauncher.launch(null)
        } else {

            permisoCamaraLauncher.launch(
                android.Manifest.permission.CAMERA
            )
        }
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Crear publicación",
            fontSize = 22.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Titulo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { openDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selectedDateText)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        ) {
            Button(
                onClick = {
                    pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
                },
                //                modifier = Modifier.padding(5.dp)
            ) {
                Text("Elegir imagen")
            }

            Button(
                onClick = {
                    tomarFoto()
                },
                //                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tomar fotografía")
            }

        }

        if (openDialog) {
            DatePickerDialog(
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
                    TextButton(onClick = { openDialog = false }) {
                        Text("Cancelar")
                    }
                }

            ) {
                DatePicker(state = datePickerState)
            }
        }

        Text("Imagen de publicación")
        ImagenCapture(url, null)

        Button(
            onClick = {
                    if (titulo.isNotEmpty() && descripcion.isNotEmpty() && url.isNotEmpty() && selectedDateText.isNotEmpty() && selectedDateText != "seleccionar fecha") {
                        coroutineScope.launch(Dispatchers.IO) {
                            dao.insertar(
                                Publication(
                                    id = 0,
                                    titulo = titulo,
                                    descripcion = descripcion,
                                    foto = url,
                                    fecha = Date(selectedDateText)
                                )
                            )
                        }
                        Toast.makeText(
                                context,
                        "Se guardo la publicación con éxito",
                        Toast.LENGTH_SHORT
                        ).show()


                    } else {

                        Toast.makeText(
                            context,
                            "Fallo al crear la publicación",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                titulo = ""
                descripcion = ""
                url = ""
                selectedDateText = "seleccionar fecha"
            },
            Modifier.padding(15.dp)
        ) {
            Text("Subir publicación")
        }


    }


}