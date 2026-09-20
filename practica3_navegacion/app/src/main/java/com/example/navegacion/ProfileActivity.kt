package com.example.navegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class ProfileActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombre = intent.getStringExtra("EXTRA_NOMBRE") ?: "Sin nombre"
        val correo = intent.getStringExtra("EXTRA_CORREO") ?: "Sin correo"

        setContent {
            ProfileScreen(
              nombre = nombre, correo = correo,
                onBackClick = {
                    finish()
                }
            )
        }
    }
}