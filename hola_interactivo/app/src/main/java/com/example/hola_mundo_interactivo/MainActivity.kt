package com.example.hola_mundo_interactivo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.hola_mundo_interactivo.ui.theme.Hola_mundo_interactivoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hola_mundo_interactivoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Menu("Input dinamico")
                    Menu("Input interactivo", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun Menu(title: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Title("Input interactivo!", modifier)
            TextField(
                value = text,
                onValueChange = { newValue ->
                    text = newValue
                    println("Current text: $newValue")
                },
                label = { Text("Escribe algo...") }
            )

            Text(
                "Escribiste $text",
                modifier = modifier
            )
        }
    }
}

@Composable
fun Title(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hola_mundo_interactivoTheme {
        Title("Android")
    }
}