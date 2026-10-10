package com.example.miniproyecto2.data

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TareaDao {
    @Query("SELECT * from tareas")
    fun obtenerTodos(): Flow<List<Tarea>>

    @Query("SELECT * from tareas where completed")
    fun obtenerCompletados(): Flow<List<Tarea>>

    @Query("SELECT * from tareas where not completed")
    fun obtenerPendientes(): Flow<List<Tarea>>


    @Query("DELETE FROM tareas where completed")
    fun eliminarCompletadas()

    @Insert
    fun insertar(tarea: Tarea)

    @Update
    fun actualizar(tarea: Tarea)

    @Delete
    fun eliminar(tarea: Tarea)
}