package com.example.miniproyecto2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tareas")
data class Tarea(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "description") val descripcion: String,
    @ColumnInfo(name = "prioridad") var prioridad: String,
    @ColumnInfo(name = "completed") var completada: Boolean,
)
