package com.example.miniproyecto3.data

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PublicationDao {
    @Query("SELECT * from publicaciones")
    fun obtenerTodos(): Flow<List<Publication>>

    @Insert
    fun insertar(publicacion: Publication)

    @Delete
    fun eliminar(publicacion: Publication)
}