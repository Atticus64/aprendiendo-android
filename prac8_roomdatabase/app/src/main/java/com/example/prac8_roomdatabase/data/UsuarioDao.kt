package com.example.prac8_roomdatabase.data

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Query("SELECT * from usuarios")
    fun obtenerTodos(): Flow<List<Usuario>>

    @Insert
    fun insertar(usuario: Usuario)

    @Delete
    fun eliminar(usuario: Usuario)
}