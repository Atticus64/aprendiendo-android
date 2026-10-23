package com.example.practica11archivosdcard

import android.media.MediaPlayer
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReproductorScreen() {

    val context = LocalContext.current

    var audioUri by remember { mutableStateOf<Uri?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }

    val selectorAudio = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            audioUri = uri


            mediaPlayer?.release()

            mediaPlayer = MediaPlayer.create(context, uri)
            mediaPlayer?.start()
            isPlaying = true

            mediaPlayer?.setOnCompletionListener {
                isPlaying = false
            }
        }

    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mini reproductor MP3") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (audioUri == null) "Ningún archivo seleccionado"  else "Audio cargado correctamente",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { selectorAudio.launch("audio/*") }) {
                Text("Buscar MP3 en SD/Memoria")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {


                FloatingActionButton(
                    onClick = {
                        if (mediaPlayer != null && !isPlaying) {
                            mediaPlayer?.start()
                            isPlaying = true
                        }
                    }
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "Reproducir audio")
                }

                FloatingActionButton(
                    onClick = {
                        if (mediaPlayer != null && isPlaying) {
                            mediaPlayer?.pause()
                            isPlaying = false
                        }
                    }
                ) {

                    Text(" | | ", style = MaterialTheme.typography.titleLarge)
                }



                FloatingActionButton(
                    onClick = {
                        if (mediaPlayer != null && isPlaying) {
                            mediaPlayer?.pause()
                            isPlaying = false
                            mediaPlayer?.seekTo(0)
                        }
                    }
                ) {
                    Icon(Icons.Default.Square, contentDescription = "Detenr audio")
                }

            }





        }

    }
}