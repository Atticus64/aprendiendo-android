package com.example.prac8_roomdatabase.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.concurrent.atomic.AtomicInteger

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nombre") val nombre: String,

)
