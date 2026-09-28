package com.example.mini_proyecto_alumno


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class ConfiguractionActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val matricula = intent.getStringExtra("EXTRA_MATRICULA") ?: "Sin matricula"
        val nombre = intent.getStringExtra("EXTRA_NOMBRE") ?: "Sin nombre"
        val carrera = intent.getStringExtra("EXTRA_CARRERA") ?: "Sin carrera"
        val turno = intent.getStringExtra("EXTRA_TURNO") ?: "Sin turno"
        val activo = intent.getBooleanExtra("EXTRA_ACTIVO", false)

        setContent {
            ConfigurationScreen(
                matricula, nombre, carrera, turno, activo,
                onBackClick = {
                    finish()
                }
            )
        }
    }

}
