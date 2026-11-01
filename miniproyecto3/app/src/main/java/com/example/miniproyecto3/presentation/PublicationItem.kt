package com.example.miniproyecto3.presentation

import android.R
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun PublicationItem(titulo: String, descripcion: String, uri: String, fecha: Date) {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    val fechaTexto = format.format(fecha)

    Card(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                titulo,
                fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle
            )
            Text(
                descripcion,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                fontStyle = FontStyle.Italic
            )
            Text("Publicado en ${fechaTexto}")
            Spacer(modifier = Modifier.height(4.dp))

            ImagenCapture(
                uri,
                200.dp
            )
        }
    }



}

