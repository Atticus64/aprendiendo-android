package com.example.mini_proyecto_alumno

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class ProfileManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context
        .getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    companion object {
        const val KEY_MATRICULA = "key_matricula"
        const val KEY_NOMBRE = "key_nombre"
        const val KEY_CARRERA = "key_carrera"
        const val KEY_TURNO = "key_turno"
        const val KEY_ESTATUS = "key_estatus"
    }

    fun saveProfile(matricula: String, nombre: String, carrera: String, turno: String, status: Boolean) {
        sharedPreferences.edit {
            putString(KEY_MATRICULA, matricula)
            putString(KEY_NOMBRE, nombre)
            putString(KEY_CARRERA, carrera)
            putString(KEY_TURNO, turno)
            putBoolean(KEY_ESTATUS, status)
        }
    }

    fun getMatricula(): String {
        return sharedPreferences.getString(KEY_MATRICULA, "") ?: ""
    }

    fun getNombre(): String {
        return sharedPreferences.getString(KEY_NOMBRE, "") ?: ""
    }

    fun getCarrera(): String {
        return sharedPreferences.getString(KEY_CARRERA, "") ?: ""
    }

    fun getTurno(): String {
        return sharedPreferences.getString(KEY_TURNO, "") ?: ""
    }

    fun getStatus(): Boolean {
        return sharedPreferences.getBoolean(KEY_ESTATUS, false)
    }


    fun deleteProfile() {
        sharedPreferences.edit { clear() }
    }

}
